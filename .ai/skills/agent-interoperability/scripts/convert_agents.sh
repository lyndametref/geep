#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
mode="${1:---sync}"

case "$mode" in
  --import)
    shift
    exec "$script_dir/import_agents.sh" "$@"
    ;;
  --export)
    shift
    exec "$script_dir/export_agents.sh" "$@"
    ;;
  --sync)
    [[ "${1:-}" == "--sync" ]] && shift
    exec "$script_dir/sync_agents.sh" "$@"
    ;;
  -h|--help)
    cat <<'EOF'
Usage: convert_agents.sh [--import|--export|--sync] [--client github|claude|opencode|gemini]...

  Compatibility wrapper that dispatches to the smaller agent scripts.
EOF
    ;;
  *)
    echo "Unknown argument: ${1:-}" >&2
    exit 1
    ;;
esac