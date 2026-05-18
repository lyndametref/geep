#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root="$(cd "$script_dir/../../../.." && pwd)"
canonical_agents_root="$repo_root/.ai/agents"
gemini_agents_root="$repo_root/.gemini/agents"
gemini_skills_link="$repo_root/.gemini/skills"

ensure_symlink() {
  local link_path="$1"
  local target_path="$2"
  local current_target

  mkdir -p "$(dirname "$link_path")"

  if [[ -L "$link_path" ]]; then
    current_target="$(readlink "$link_path")"
    if [[ "$current_target" == "$target_path" ]]; then
      return 0
    fi
    rm "$link_path"
  elif [[ -e "$link_path" ]]; then
    echo "Refusing to replace non-symlink path: $link_path" >&2
    exit 1
  fi

  ln -s "$target_path" "$link_path"
}

usage() {
  cat <<'EOF'
Usage: client_gemini.sh --export|--import|--create-skill-link|--create_skill_link [--agent <slug>]...

Gemini Agent Interoperability Script
-------------------------------------

Exports or imports agent definitions between the canonical agnostic format (.ai/agents) and the Gemini format (.gemini/agents).

Options:
  --export         Export agents from .ai/agents to .gemini/agents in Gemini format.
  --import         Import agents from .gemini/agents to .ai/agents in canonical format.
  --create-skill-link, --create_skill_link
                   Create/refresh .gemini/skills symlink to ../.ai/skills/.
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

  # Create or refresh Gemini skills symlink
  ./client_gemini.sh --create-skill-link

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

extract_allowed_skills_from_text() {
  local text="$1"
  printf '%s\n' "$text" | awk '
    BEGIN { capture=0 }
    {
      if (!capture && $0 ~ /^Allowed skills:[[:space:]]*$/) {
        capture=1
        next
      }
      if (capture) {
        if ($0 ~ /^[[:space:]]*-[[:space:]]*[^[:space:]].*$/) {
          line=$0
          sub(/^[[:space:]]*-[[:space:]]*/, "", line)
          sub(/[[:space:]]+$/, "", line)
          print line
          next
        }
        if ($0 ~ /^[[:space:]]*$/) {
          next
        }
        exit
      }
    }
  '
}

extract_allowed_skills_from_markdown_frontmatter() {
  local source_file="$1"
  awk '
    function trim(v) {
      gsub(/^[[:space:]]+/, "", v)
      gsub(/[[:space:]]+$/, "", v)
      gsub(/^"/, "", v)
      gsub(/"$/, "", v)
      gsub(/^\047/, "", v)
      gsub(/\047$/, "", v)
      return v
    }
    BEGIN { in_frontmatter=0; capture_list=0 }
    NR == 1 && /^---$/ { in_frontmatter=1; next }
    in_frontmatter && /^---$/ { exit }
    in_frontmatter {
      lowered=$0
      sub(/^[[:space:]]+/, "", lowered)
      split(lowered, parts, ":")
      key=parts[1]
      if (key ~ /^(allowed-skills|allowed_skills|allowedSkills|skills)$/) {
        line=$0
        sub(/^[^:]*:[[:space:]]*/, "", line)
        if (line ~ /^\[/) {
          gsub(/^\[/, "", line)
          gsub(/\]$/, "", line)
          n=split(line, arr, /,/) 
          for (i=1; i<=n; i++) {
            value=trim(arr[i])
            if (value != "") print value
          }
          capture_list=0
        } else if (line ~ /^$/) {
          capture_list=1
        } else {
          value=trim(line)
          if (value != "") print value
          capture_list=0
        }
        next
      }
      if (capture_list) {
        if ($0 ~ /^[[:space:]]*-[[:space:]]+/) {
          value=$0
          sub(/^[[:space:]]*-[[:space:]]+/, "", value)
          value=trim(value)
          if (value != "") print value
          next
        }
        if ($0 ~ /^[[:space:]]*$/) {
          next
        }
        capture_list=0
      }
    }
  ' "$source_file"
}

unique_skill_list() {
  local combined="$1"
  awk '
    {
      line=$0
      sub(/^[[:space:]]+/, "", line)
      sub(/[[:space:]]+$/, "", line)
      if (line == "") next
      if (!seen[line]++) print line
    }
  ' <<< "$combined"
}

merge_skill_lists() {
  local first_list="$1"
  local second_list="$2"
  unique_skill_list "$(printf '%s\n%s\n' "$first_list" "$second_list")"
}

remove_allowed_skills_section() {
  local text="$1"
  printf '%s\n' "$text" | awk '
    BEGIN { skipping=0 }
    {
      if (!skipping && $0 ~ /^Allowed skills:[[:space:]]*$/) {
        skipping=1
        next
      }
      if (skipping) {
        if ($0 ~ /^[[:space:]]*-[[:space:]]*[^[:space:]].*$/) {
          next
        }
        if ($0 ~ /^[[:space:]]*$/) {
          next
        }
        skipping=0
      }
      print
    }
  '
}

format_allowed_skills_block() {
  local skills_list="$1"
  local formatted
  formatted="Allowed skills:"
  while IFS= read -r skill; do
    [[ -z "$skill" ]] && continue
    formatted+=$'\n'
    formatted+="  - $skill"
  done <<< "$skills_list"
  printf '%s\n' "$formatted"
}

upsert_allowed_skills_in_body() {
  local body="$1"
  local skills_list="$2"
  local unique
  local cleaned
  local block

  unique="$(unique_skill_list "$skills_list")"
  cleaned="$(remove_allowed_skills_section "$body")"

  if [[ -z "$unique" ]]; then
    printf '%s\n' "$cleaned"
    return 0
  fi

  block="$(format_allowed_skills_block "$unique")"

  printf '%s\n' "$cleaned" | awk -v block="$block" '
    BEGIN { inserted=0 }
    {
      if (!inserted && $0 ~ /^Hints on arguments the user can provide:[[:space:]]*$/) {
        print block
        print ""
        inserted=1
      }
      print
    }
    END {
      if (!inserted) {
        print ""
        print block
      }
    }
  '
}

emit_allowed_skills_frontmatter() {
  local skills_list="$1"
  local unique
  unique="$(unique_skill_list "$skills_list")"
  [[ -z "$unique" ]] && return 0
  printf '%s\n' 'allowed-skills:'
  while IFS= read -r skill; do
    [[ -z "$skill" ]] && continue
    printf '  - "%s"\n' "$skill"
  done <<< "$unique"
}

action="export"
selected_agents=()
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
    --create-skill-link|--create_skill_link)
      action="create_skill_link"
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

create_skill_link() {
  ensure_symlink "$gemini_skills_link" "../.ai/skills/"
  echo "Linked Gemini skills directory to .ai/skills."
}

export_one_gemini_agent() {
  local source_file="$1"
  local agent_slug="$2"
  local target_file="$gemini_agents_root/${agent_slug}.md"
  local title description source_body allowed_skills

  title="$(extract_frontmatter_value "$source_file" name)"
  [[ -z "$title" ]] && title="$(extract_frontmatter_value "$source_file" title)"
  [[ -z "$title" ]] && title="$(extract_first_heading "$source_file")"
  [[ -z "$title" ]] && title="$agent_slug"

  description="$(extract_frontmatter_value "$source_file" description)"
  [[ -z "$description" ]] && description="$(extract_summary "$source_file")"
  [[ -z "$description" ]] && description="$title"
  description="$(sanitize_description "$description")"

  source_body="$(strip_frontmatter "$source_file")"
  allowed_skills="$(extract_allowed_skills_from_text "$source_body")"
  source_body="$(upsert_allowed_skills_in_body "$source_body" "$allowed_skills")"

  mkdir -p "$(dirname "$target_file")"

  {
    printf '%s\n' '---'
    printf 'title: "%s"\n' "$title"
    printf 'description: "%s"\n' "$description"
    emit_allowed_skills_frontmatter "$allowed_skills"
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
  local title description source_body allowed_skills_from_body allowed_skills_from_frontmatter allowed_skills

  title="$(extract_frontmatter_value "$source_file" title)"
  [[ -z "$title" ]] && title="$agent_slug"

  description="$(extract_frontmatter_value "$source_file" description)"
  [[ -z "$description" ]] && description="$title"

  source_body="$(strip_frontmatter "$source_file")"
  allowed_skills_from_body="$(extract_allowed_skills_from_text "$source_body")"
  allowed_skills_from_frontmatter="$(extract_allowed_skills_from_markdown_frontmatter "$source_file")"
  allowed_skills="$(merge_skill_lists "$allowed_skills_from_body" "$allowed_skills_from_frontmatter")"
  source_body="$(upsert_allowed_skills_in_body "$source_body" "$allowed_skills")"

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
elif [[ "$action" == "create_skill_link" ]]; then
  create_skill_link
else
  echo "Unknown action: $action" >&2
  exit 1
fi
