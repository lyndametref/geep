---
name: agent-interoperability
description: 'Manage agent definitions across multiple clients (Claude, OpenCode, GitHub Copilot, Gemini) by importing/exporting agents to/from the canonical .ai/agents format.'
argument-hint: 'Optional: import agents, export agents, or create symlinks.'
user-invocable: true
disable-model-invocation: false
---

# Agent Interoperability

## Outcome
Ensure `.ai/agents` remains the canonical source of truth for agent definitions. Facilitate seamless import/export of agents between `.ai/agents` and client-specific formats (Claude, OpenCode, GitHub Copilot, Gemini).

## When To Use
- You need to synchronize agent definitions between `.ai/agents` and client-specific directories.
- You want to export canonical agents to client-ready formats.
- You need to import client-specific agents into the canonical `.ai/agents` format.
- You want to create symlinks for shared skills.

## Procedure
1. **Client-Specific Scripts**:
   - Use the following scripts for import/export/link operations:
      - **GitHub Copilot**: `./.ai/skills/agent-interoperability/scripts/client_github.sh --import|--export|--create-skill-link|--create_skill_link`
      - **Claude Code**: `./.ai/skills/agent-interoperability/scripts/client_claude.sh --import|--export|--create-skill-link|--create_skill_link`
      - **OpenCode**: `./.ai/skills/agent-interoperability/scripts/client_opencode.sh --import|--export|--create-skill-link|--create_skill_link`
      - **Gemini**: `./.ai/skills/agent-interoperability/scripts/client_gemini.sh --import|--export|--create-skill-link|--create_skill_link`
   - Add `--agent <slug>` to process specific agents.

2. **Import Agents**:
   - Import agents from client-specific directories into `.ai/agents`:
     ```bash
     ./client_<client>.sh --import
     ```
   - Example: `./client_claude.sh --import --agent my-agent`

3. **Export Agents**:
   - Export agents from `.ai/agents` to client-specific formats:
     ```bash
     ./client_<client>.sh --export
     ```
   - Example: `./client_github.sh --export`
   - Metadata conversion rule: each client script converts the canonical `Allowed skills:` metadata section to the client file metadata when supported (`allowed-skills` in YAML frontmatter), while preserving the canonical section in agent content.

4. **Import Metadata Preservation**:
   - Import operations normalize and preserve `Allowed skills:` in canonical format exactly as used in `.ai/agents/<slug>/AGENT.md` (same structure as the terminologist agent).
   - If a client file includes `allowed-skills` metadata, it is merged into the canonical `Allowed skills:` section without duplicates.

5. **Verify Outputs**:
   - Ensure generated files exist in the appropriate directories.
   - Example: `.claude/agents/<slug>.json`, `.github/agents/<slug>.md`.

6. **Skill Linking**:
   - For Gemini, use the Gemini client script action:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_gemini.sh --create-skill-link
      ```
   - Alternate alias accepted by the script:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_gemini.sh --create_skill_link
      ```
   - For Claude Code, use the Claude client script action:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_claude.sh --create-skill-link
      ```
   - Alternate alias accepted by the script:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_claude.sh --create_skill_link
      ```
   - For GitHub Copilot, use the GitHub client script action:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_github.sh --create-skill-link
      ```
   - Alternate alias accepted by the script:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_github.sh --create_skill_link
      ```
   - For OpenCode, use only the OpenCode client script action:
       ```bash
      ./.ai/skills/agent-interoperability/scripts/client_opencode.sh --create-skill-link
       ```
   - Alternate alias accepted by the script:
      ```bash
      ./.ai/skills/agent-interoperability/scripts/client_opencode.sh --create_skill_link
      ```
   - Do not rely on shared linker scripts when working with OpenCode.

## Decision Points
- If conflicts arise (e.g., multiple client files for the same agent slug), prioritize the first valid source and log provenance in the canonical file.
- Ensure all client-specific formats adhere to their respective schemas (e.g., JSON for Claude, YAML for OpenCode).

## Notes
- These scripts are client-centric and designed for modularity.
- Avoid direct edits to `.ai/agents` or client directories; use the provided scripts for consistency.
