#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck source=./common.sh
source "$script_dir/common.sh"

usage() {
  cat <<'EOF'
Usage: export_agents.sh [--client github|claude|opencode|gemini]...

  Render .ai/agents back into client-ready markdown files.
EOF
}

selected_clients=()

write_client_agent() {
  local source_file="$1"
  local client_name="$2"
  local agent_slug="$3"
  local target_file="$4"
  local title description source_body

  title="$(extract_frontmatter_value "$source_file" name)"
  [[ -z "$title" ]] && title="$(extract_frontmatter_value "$source_file" title)"
  [[ -z "$title" ]] && title="$(extract_first_heading "$source_file")"
  [[ -z "$title" ]] && title="$agent_slug"

  description="$(extract_frontmatter_value "$source_file" description)"
  [[ -z "$description" ]] && description="$(extract_summary "$source_file")"
  [[ -z "$description" ]] && description="$title"

  source_body="$(strip_frontmatter "$source_file")"
  mkdir -p "$(dirname "$target_file")"

  {
    printf '%s\n' '---'
    printf 'name: "%s"\n' "$(yaml_escape "$title")"
    printf 'description: "%s"\n' "$(yaml_escape "$description")"
    printf 'source-client: "%s"\n' "$(yaml_escape "$client_name")"
    printf 'source-path: "%s"\n' "$(yaml_escape "${source_file#$repo_root/}")"
    printf '%s\n\n' '---'
    if [[ -n "$source_body" ]]; then
      printf '%s\n' "$source_body"
    fi
  } > "$target_file"
}

export_canonical_agents_for_client() {
  local client_name="$1"
  local files file agent_slug

  mapfile -d '' files < <(find "$canonical_agents_root" -mindepth 2 -maxdepth 2 -name AGENT.md -print0 | sort -z)
  if [[ ${#files[@]} -eq 0 ]]; then
    echo "No agent definitions found under $canonical_agents_root" >&2
    return 1
  fi

  for file in "${files[@]}"; do
    agent_slug="$(basename "$(dirname "$file")")"
    write_client_agent "$file" "$client_name" "$agent_slug" "$(client_target_file "$client_name" "$agent_slug")"
  done
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --client)
      if [[ $# -lt 2 ]]; then
        echo "--client requires a value" >&2
        usage
        exit 1
      fi
      selected_clients+=("$2")
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

if [[ ${#selected_clients[@]} -eq 0 ]]; then
  selected_clients=(github claude opencode gemini)
fi

for client_name in "${selected_clients[@]}"; do
  export_canonical_agents_for_client "$client_name"
done

echo "Exported canonical agent files into selected client directories."
