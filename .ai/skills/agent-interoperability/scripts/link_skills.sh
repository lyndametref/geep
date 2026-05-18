#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root="$(cd "$script_dir/../../../.." && pwd)"

client_skill_links=(
  "$repo_root/.github/skills"
  "$repo_root/.claude/skills"
  "$repo_root/.opencode/skills"
  "$repo_root/.gemini/skills"
)

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

for client_skill_link in "${client_skill_links[@]}"; do
  ensure_symlink "$client_skill_link" "../.ai/skills/"
done

echo "Linked client skills directories to .ai/skills."