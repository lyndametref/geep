#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root="$(cd "$script_dir/../../../.." && pwd)"

canonical_agents_root="$repo_root/.ai/agents"
github_agents_root="$repo_root/.github/agents"
claude_agents_root="$repo_root/.claude/agents"
opencode_agents_root="$repo_root/.opencode/agents"
gemini_agents_root="$repo_root/.gemini/agents"

yaml_escape() {
  local value="$1"
  value="${value//\\/\\\\}"
  value="${value//\"/\\\"}"
  printf '%s' "$value"
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
    /^# / { next }
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

display_name_for_client() {
  local client_name="$1"

  case "$client_name" in
    github) printf '%s' 'GitHub Copilot' ;;
    claude) printf '%s' 'Claude Code' ;;
    opencode) printf '%s' 'OpenCode' ;;
    gemini) printf '%s' 'Gemini' ;;
    *) printf '%s' "$client_name" ;;
  esac
}

client_root_for_name() {
  local client_name="$1"

  case "$client_name" in
    github) printf '%s' "$github_agents_root" ;;
    claude) printf '%s' "$claude_agents_root" ;;
    opencode) printf '%s' "$opencode_agents_root" ;;
    gemini) printf '%s' "$gemini_agents_root" ;;
    *) echo "Unknown client: $client_name" >&2; exit 1 ;;
  esac
}

client_target_file() {
  local client_name="$1"
  local agent_slug="$2"

  case "$client_name" in
    github) printf '%s/%s.agent.md' "$(client_root_for_name "$client_name")" "$agent_slug" ;;
    claude|opencode|gemini) printf '%s/%s.md' "$(client_root_for_name "$client_name")" "$agent_slug" ;;
    *) echo "Unknown client: $client_name" >&2; exit 1 ;;
  esac
}

canonical_target_file() {
  local agent_slug="$1"
  printf '%s/%s/AGENT.md' "$canonical_agents_root" "$agent_slug"
}
