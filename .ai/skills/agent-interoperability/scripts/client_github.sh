#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"


repo_root="$(cd "$script_dir/../../../.." && pwd)"
canonical_agents_root="$repo_root/.ai/agents"
github_agents_root="$repo_root/.github/agents"
selected_agents=()
action="export"

usage() {
  cat <<'EOF'
Usage: client_github.sh --export|--import [--agent <slug>]...

GitHub Copilot Agent Interoperability Script
--------------------------------------------

Exports and imports agent definitions between the canonical agnostic format (.ai/agents) and the GitHub Copilot format (.github/agents).

Options:
  --export         Export agents from .ai/agents to .github/agents in GitHub Copilot format.
  --import         Import agents from .github/agents to .ai/agents (agnostic format).
  --agent <slug>   Restrict to a specific agent slug (can be repeated).
  -h, --help       Show this help message and exit.

Export details:
  - Reads each agent from .ai/agents/<slug>/AGENT.md
  - Writes to .github/agents/<slug>.md in GitHub Copilot format (YAML frontmatter: title, description)
  - If --agent is not specified, processes all agents

Import details:
  - Reads each agent from .github/agents/<slug>.md
  - Writes to .ai/agents/<slug>/AGENT.md in agnostic format (YAML frontmatter: title, description)
  - If --agent is not specified, processes all agents

Examples:
  # Export all agents to GitHub Copilot format
  ./client_github.sh --export

  # Import all agents from GitHub Copilot format
  ./client_github.sh --import

  # Export only a specific agent
  ./client_github.sh --export --agent my-agent

  # Import only a specific agent
  ./client_github.sh --import --agent my-agent
EOF
}


slugify() {
  local value="$1"
  value="$(printf '%s' "$value" | tr '[:upper:]' '[:lower:]')"
  value="$(printf '%s' "$value" | sed 's/[[:space:]_.]/-/g; s/[^a-z0-9-]//g; s/-\+/-/g; s/^-//; s/-$//')"
  printf '%s' "$value"
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --export)
      action="export"
      shift
      ;;
    --import)
      action="import"
      shift
      ;;
    --agent)
      if [[ $# -lt 2 ]]; then
        echo "--agent requires a value" >&2
        usage
        exit 1
      fi
      selected_agents+=("$(slugify "$2")")
      shift 2
      ;;
    -h|--help)
      usage
      exit 0
      ;;
    *)
      echo "Unknown argument: $1" >&2
      usage
      exit 1
      ;;
  esac
done

echo "Completed ${action} for ${client_name} agents."

extract_frontmatter_value() {
  local source_file="$1"
  local key="$2"
  awk -v key="$key" '
    BEGIN { in_frontmatter=0 }
    NR == 1 && /^---$/ { in_frontmatter=1; next }
    in_frontmatter && /^---$/ { exit }
    in_frontmatter {
      if ($0 ~ "^[[:space:]]*" key ":[[:space:]]*") {
        value=$0
        sub("^[[:space:]]*" key ":[[:space:]]*", "", value)
        sub(/^[[:space:]]+/, "", value)
        sub(/[[:space:]]+$/, "", value)
        if (value ~ /^".*"$/) {
          sub(/^"/, "", value)
          sub(/"$/, "", value)
        }
        print value
        exit
      }
    }
  ' "$source_file"
}

# Export logic: agnostic -> GitHub Copilot
import_one_github_agent() {
  local source_file="$1"
  local agent_slug="$2"
  local target_dir="$canonical_agents_root/$agent_slug"
  local target_file="$target_dir/AGENT.md"
  local title description
  local body

  # Extract frontmatter
  title="$(awk '/^---$/ {f++} f==1 && /^title:/ {sub(/^title:[[:space:]]*/, ""); sub(/^"/, ""); sub(/"$/, ""); print; exit}' "$source_file")"
  description="$(awk '/^---$/ {f++} f==1 && /^description:/ {sub(/^description:[[:space:]]*/, ""); sub(/^"/, ""); sub(/"$/, ""); print; exit}' "$source_file")"

  # Extract body (after second ---)
  body="$(awk 'BEGIN{frontmatter=0} /^---$/ {frontmatter++; next} frontmatter<2{next} {print}' "$source_file")"

  mkdir -p "$target_dir"
  {
    printf '---\n'
    printf 'title: "%s"\n' "$title"
    printf 'description: "%s"\n' "$description"
    printf '---\n\n'
    if [[ -n "$body" ]]; then
      printf '%s\n' "$body"
    fi
  } > "$target_file"
  printf 'Imported github agent: %s\n' "$agent_slug"
}

main_import() {
  local file agent_slug
  local processed_count=0
  while IFS= read -r -d '' file; do
    agent_slug="$(basename "${file%.md}")"
    if [[ ${#selected_agents[@]} -gt 0 ]]; then
      local found=0
      for sel in "${selected_agents[@]}"; do
        if [[ "$sel" == "$agent_slug" ]]; then found=1; break; fi
      done
      [[ $found -eq 0 ]] && continue
    fi
    import_one_github_agent "$file" "$agent_slug"
    processed_count=$((processed_count + 1))
  done < <(find "$github_agents_root" -mindepth 1 -maxdepth 1 -name '*.md' -print0 | sort -z)
  if [[ $processed_count -eq 0 ]]; then
    echo "No matching agents found for import." >&2
    exit 1
  fi
}
extract_frontmatter_value() {
  local source_file="$1"
  local key="$2"
  awk -v key="$key" '
    BEGIN { in_frontmatter=0 }
    NR == 1 && /^---$/ { in_frontmatter=1; next }
    in_frontmatter && /^---$/ { exit }
    in_frontmatter {
      if ($0 ~ "^[[:space:]]*" key ":[[:space:]]*") {
        value=$0
        sub("^[[:space:]]*" key ":[[:space:]]*", "", value)
        sub(/^[[:space:]]+/, "", value)
        sub(/[[:space:]]+$/, "", value)
        if (value ~ /^".*"$/) {
          sub(/^"/, "", value)
          sub(/"$/, "", value)
        }
        print value
        exit
      }
    }
  ' "$source_file"
}

extract_first_heading() {
  local source_file="$1"
  awk '
    BEGIN { in_frontmatter=0 }
    NR == 1 && /^---$/ { in_frontmatter=1; next }
    in_frontmatter && /^---$/ { exit }
    in_frontmatter { next }
    /^# / {
      sub(/^# +/, "", $0)
      print
      exit
    }
  ' "$source_file"
}

extract_summary() {
  local source_file="$1"
  local summary
  summary="$(extract_frontmatter_value "$source_file" description)"
  if [[ -n "$summary" ]]; then
    printf '%s' "$summary"
    return 0
  fi
  awk '
    BEGIN { in_frontmatter=0; summary="" }
    NR == 1 && /^---$/ { in_frontmatter=1; next }
    in_frontmatter && /^---$/ { in_frontmatter=0; next }
    in_frontmatter { next }
    /^#+[[:space:]]+/ { next }
    /^[[:space:]]*$/ {
      if (summary != "") {
        exit
      }
      next
    }
    {
      line=$0
      sub(/^[[:space:]]+/, "", line)
      sub(/[[:space:]]+$/, "", line)
      if (line != "") {
        if (summary == "") {
          summary = line
        } else {
          summary = summary " " line
        }
      }
    }
    END {
      gsub(/[[:space:]]+/, " ", summary)
      sub(/^[[:space:]]+/, "", summary)
      sub(/[[:space:]]+$/, "", summary)
      print summary
    }
  ' "$source_file"
}

strip_frontmatter() {
  local source_file="$1"
  awk '
    BEGIN { in_frontmatter=0; frontmatter_complete=0 }
    NR == 1 && /^---$/ { in_frontmatter=1; next }
    in_frontmatter && /^---$/ { in_frontmatter=0; frontmatter_complete=1; next }
    in_frontmatter { next }
    frontmatter_complete { print; next }
    { print }
  ' "$source_file"
}

sanitize_description() {
  local value="$1"
  value="$(printf '%s' "$value" | sed -E 's/^[[:space:]]+//; s/[[:space:]]+$//; s/^#+[[:space:]]*//')"
  if [[ -z "$value" ]]; then
    value="Agent prompt"
  fi
  printf '%s' "$value"
}

export_one_github_agent() {
  local source_file="$1"
  local agent_slug="$2"
  local target_file="$github_agents_root/${agent_slug}.md"
  local title description source_body

  title="$(extract_frontmatter_value "$source_file" name)"
  [[ -z "$title" ]] && title="$(extract_frontmatter_value "$source_file" title)"
  [[ -z "$title" ]] && title="$(extract_first_heading "$source_file")"
  [[ -z "$title" ]] && title="$agent_slug"

  description="$(extract_frontmatter_value "$source_file" description)"
  [[ -z "$description" ]] && description="$(extract_summary "$source_file")"
  [[ -z "$description" ]] && description="$title"
  description="$(sanitize_description "$description")"

  source_body="$(strip_frontmatter "$source_file")"

  mkdir -p "$(dirname "$target_file")"

  {
    printf '%s\n' '---'
    printf 'title: "%s"\n' "$title"
    printf 'description: "%s"\n' "$description"
    printf '%s\n\n' '---'
    if [[ -n "$source_body" ]]; then
      printf '%s\n' "$source_body"
    fi
  } > "$target_file"
  printf 'Exported github agent: %s\n' "$agent_slug"
}

main_export() {
  local file agent_slug
  local processed_count=0
  while IFS= read -r -d '' file; do
    agent_slug="$(basename "$(dirname "$file")")"
    if [[ ${#selected_agents[@]} -gt 0 ]]; then
      local found=0
      for sel in "${selected_agents[@]}"; do
        if [[ "$sel" == "$agent_slug" ]]; then found=1; break; fi
      done
      [[ $found -eq 0 ]] && continue
    fi
    export_one_github_agent "$file" "$agent_slug"
    processed_count=$((processed_count + 1))
  done < <(find "$canonical_agents_root" -mindepth 2 -maxdepth 2 -name AGENT.md -print0 | sort -z)
  if [[ $processed_count -eq 0 ]]; then
    echo "No matching agents found for export." >&2
    exit 1
  fi
}

if [[ "$action" == "export" ]]; then
  main_export
  echo "Completed export for github agents."
elif [[ "$action" == "import" ]]; then
  main_import
  echo "Completed import for github agents."
else
  echo "Unknown action: $action" >&2
  exit 1
fi
