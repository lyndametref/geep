---
name: agent-interoperability
description: 'Import agent definitions from Claude Code, OpenCode, GitHub Copilot, and Gemini into the canonical .ai/agents format; export canonical agents back to those clients; and expose .ai/skills via POSIX symlinks.'
argument-hint: 'Optional: import agents, export agents, sync both directions, or link skills.'
user-invocable: true
disable-model-invocation: false
---

# Agent Interoperability

## Outcome
Keep `.ai/agents` as the canonical source of truth, import agents from any supported client layout into that format, export canonical agents back to client-ready files, and make skills from `.ai/skills` available to multiple AI clients using symbolic links.

## When To Use
- You add or update an agent in `.ai/agents` and need synced versions for multiple AI clients.
- You create an agent in `.github/agents`, `.claude/agents`, `.opencode/agents`, or `.gemini/agents` and want it normalized into `.ai/agents`.
- You want one source of truth for skills in `.ai/skills`.
- You need repeatable, idempotent setup scripts for local onboarding.

## Procedure
1. Import client agents into the canonical format:
   - Run `./.ai/skills/agent-interoperability/scripts/import_agents.sh`.
   - This scans supported client layouts and writes normalized agents into `.ai/agents/*/AGENT.md`.
2. Export canonical agents back to clients when needed:
   - Run `./.ai/skills/agent-interoperability/scripts/export_agents.sh`.
   - This regenerates client-ready files in:
     - `.github/agents/*.agent.md`
     - `.claude/agents/*.md`
     - `.opencode/agents/*.md`
     - `.gemini/agents/*.md`
3. Or do both at once:
   - Run `./.ai/skills/agent-interoperability/scripts/sync_agents.sh`.
   - `./.ai/skills/agent-interoperability/scripts/convert_agents.sh` remains as a compatibility wrapper.
2. Link shared skills into client paths:
   - Run `./.ai/skills/agent-interoperability/scripts/link_skills.sh`.
   - This creates/updates symlinks from each client skills directory to each skill under `.ai/skills`.
3. Verify outputs:
   - Confirm generated files exist in the canonical and client target directories.
   - Confirm links with `ls -l .github/skills .claude/skills .opencode/skills .gemini/skills`.

## Decision Points
- If multiple client files map to the same agent slug, the importer keeps the first supported source it finds and records source provenance in the canonical file.
- If a client has no official agent schema, keep generated files as descriptive markdown prompts for manual use.
- If source content lacks frontmatter, fall back to the filename and first heading for normalization.

## Quality Checks
- Every supported client-format agent can be normalized into `.ai/agents/*/AGENT.md`.
- Every `.ai/agents/*/AGENT.md` can be regenerated into each target client directory.
- Conversion is idempotent (re-running scripts updates files and links cleanly).
- Skills remain authored once under `.ai/skills` and exposed via symlinks only.

## Scripts
- [import_agents.sh](./scripts/import_agents.sh)
- [export_agents.sh](./scripts/export_agents.sh)
- [sync_agents.sh](./scripts/sync_agents.sh)
- [convert_agents.sh](./scripts/convert_agents.sh)
- [link_skills.sh](./scripts/link_skills.sh)
