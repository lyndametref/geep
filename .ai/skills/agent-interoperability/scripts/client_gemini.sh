#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root="$(cd "$script_dir/../../../.." && pwd)"
canonical_agents_root="$repo_root/.ai/agents"
gemini_agents_root="$repo_root/.gemini/agents"

usage() {
  cat <<'EOF'
Usage: client_gemini.sh --export [--agent <slug>]... | --import [--agent <slug>]...

Gemini Agent Interoperability Script
-------------------------------------

Exports or imports agent definitions between the canonical agnostic format (.ai/agents) and the Gemini format (.gemini/agents).

Options:
  --export         Export agents from .ai/agents to .gemini/agents in Gemini format.
  --import         Import agents from .gemini/agents to .ai/agents in canonical format.
  --agent <slug>   Restrict to a specific agent slug (can be repeated).
  -h, --help       Show this help message and exit.

Export details:
  - Reads each agent from .ai/agents/<slug>/AGENT.md
  - Writes to .gemini/agents/<slug>.md in Gemini format (YAML frontmatter: title, description)
  - If --agent is not specified, processes all agents

Import details:
  - Reads each agent from .gemini/agents/<slug>.md
  - Writes to .ai/agents/<slug>/AGENT.md in canonical format
  - If --agent is not specified, processes all agents

Examples:
  # Export all agents to Gemini format
  ./client_gemini.sh --export

  # Export only a specific agent
  ./client_gemini.sh --export --agent my-agent

  # Import all agents from Gemini format
  ./client_gemini.sh --import

  # Import only a specific agent
  ./client_gemini.sh --import --agent my-agent
EOF
}

slugify() {
  local value="$1"
  value="$(printf '%s' "$value" | tr '[:upper:]' '[:lower:]')"
  value="$(printf '%s' "$value" | sed 's/[[:space:]_.]/-/g; s/[^a-z0-9-]//g; s/-\+/-/g; s/^-//; s/-$//')"
  printf '%s' "$value"
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

export_one_gemini_agent() {
  local source_file="$1"
  local agent_slug="$2"
  local target_file="$gemini_agents_root/${agent_slug}.md"
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
  printf 'Exported gemini agent: %s\n' "$agent_slug"
}

import_one_gemini_agent() {
  local source_file="$1"
  local agent_slug="$2"
  local target_dir="$canonical_agents_root/$agent_slug"
  local target_file="$target_dir/AGENT.md"
  local title description source_body

  title="$(extract_frontmatter_value "$source_file" title)"
  [[ -z "$title" ]] && title="$agent_slug"

  description="$(extract_frontmatter_value "$source_file" description)"
  [[ -z "$description" ]] && description="$title"

  source_body="$(strip_frontmatter "$source_file")"

  mkdir -p "$target_dir"

  {
    printf '%s\n' '---'
    printf 'name: "%s"\n' "$title"
    printf 'description: "%s"\n' "$description"
    printf '%s\n\n' '---'
    if [[ -n "$source_body" ]]; then
      printf '%s\n' "$source_body"
    fi
  } > "$target_file"
  printf 'Imported gemini agent: %s\n' "$agent_slug"
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
    export_one_gemini_agent "$file" "$agent_slug"
    processed_count=$((processed_count + 1))
  done < <(find "$canonical_agents_root" -mindepth 2 -maxdepth 2 -name AGENT.md -print0 | sort -z)
  if [[ $processed_count -eq 0 ]]; then
    echo "No matching agents found for export." >&2
    exit 1
  fi
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
    import_one_gemini_agent "$file" "$agent_slug"
    processed_count=$((processed_count + 1))
  done < <(find "$gemini_agents_root" -mindepth 1 -maxdepth 1 -name '*.md' -print0 | sort -z)
  if [[ $processed_count -eq 0 ]]; then
    echo "No matching agents found for import." >&2
    exit 1
  fi
}

if [[ "$action" == "export" ]]; then
  main_export
  echo "Completed export for gemini agents."
elif [[ "$action" == "import" ]]; then
  main_import
  echo "Completed import for gemini agents."
else
  echo "Invalid action. Use --export or --import."
  exit 1
fi
