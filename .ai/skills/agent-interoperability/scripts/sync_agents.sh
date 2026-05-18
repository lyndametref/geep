#!/usr/bin/env bash

set -euo pipefail

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

"$script_dir/import_agents.sh" "$@"
"$script_dir/export_agents.sh" "$@"

echo "Imported client agents into .ai/agents and regenerated client-ready outputs."
