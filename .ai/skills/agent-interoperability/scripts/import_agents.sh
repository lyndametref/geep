#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
# shellcheck source=./common.sh
source "$script_dir/common.sh"

usage() {
  cat <<'EOF'
Usage: import_agents.sh [--client github|claude|opencode|gemini]...

  Normalize supported client agent layouts into .ai/agents.
EOF
}

declare -A seen_slugs=()
selected_clients=()

write_canonical_agent() {
  local source_file="$1"
  local source_client="$2"
  local agent_slug="$3"
  local target_file="$4"
  local title description source_path source_body

  title="$(extract_frontmatter_value "$source_file" name)"
  [[ -z "$title" ]] && title="$(extract_frontmatter_value "$source_file" title)"
  [[ -z "$title" ]] && title="$(extract_first_heading "$source_file")"
  [[ -z "$title" ]] && title="$agent_slug"

  description="$(extract_frontmatter_value "$source_file" description)"
  [[ -z "$description" ]] && description="$(extract_summary "$source_file")"
  [[ -z "$description" ]] && description="$title"

  source_path="${source_file#$repo_root/}"
  source_body="$(strip_frontmatter "$source_file")"
  mkdir -p "$(dirname "$target_file")"

  {
    printf '%s\n' '---'
    printf 'name: "%s"\n' "$(yaml_escape "$title")"
    printf 'description: "%s"\n' "$(yaml_escape "$description")"
    printf 'source-client: "%s"\n' "$(yaml_escape "$source_client")"
    printf 'source-path: "%s"\n' "$(yaml_escape "$source_path")"
    printf '%s\n\n' '---'
    printf '# %s\n\n' "$title"
    printf '## Mission statement\n\n'
    printf '%s\n\n' "$description"
    printf '## Metadata\n\n'
    printf 'Source client:\n'
    printf '    - %s\n' "$(display_name_for_client "$source_client")"
    printf 'Source path:\n'
    printf '    - %s\n\n' "$source_path"
    printf '## Imported source\n\n'
    if [[ -n "$source_body" ]]; then
      printf '%s\n' "$source_body"
    else
      printf '%s\n' '(source body empty)'
    fi
  } > "$target_file"
}

import_client_root() {
  local client_name="$1"
  local client_root files file base_name agent_slug target_file

  client_root="$(client_root_for_name "$client_name")"
  [[ -d "$client_root" ]] || return 0

  mapfile -d '' files < <(find "$client_root" -maxdepth 1 -type f -name '*.md' -print0 | sort -z)
  [[ ${#files[@]} -gt 0 ]] || return 0

  for file in "${files[@]}"; do
    base_name="$(basename "$file")"
    agent_slug="${base_name%.md}"
    agent_slug="${agent_slug%.agent}"
    agent_slug="$(slugify "$agent_slug")"
    [[ -z "$agent_slug" ]] && agent_slug="$(slugify "$(extract_first_heading "$file")")"
    [[ -z "$agent_slug" ]] && agent_slug="$(basename "$client_root")"

    if [[ -n "${seen_slugs[$agent_slug]+x}" ]]; then
      continue
    fi
    seen_slugs["$agent_slug"]=1

    target_file="$(canonical_target_file "$agent_slug")"
    write_canonical_agent "$file" "$client_name" "$agent_slug" "$target_file"
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
  import_client_root "$client_name"
done

echo "Imported supported client agent files into .ai/agents."
