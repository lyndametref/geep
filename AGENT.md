# AGENT.md

## Purpose
This file defines how coding and documentation agents should operate in this repository.

## Single repository
This repository will contain all the applications for the Geep ecosystem as well as the backlog and the requirements.
Here is the structure:
- backlog/ : Tasks, project-specific docs, and decisions.
- docs/ : Domain reference material and requirements.
- appAndroid/ : Android application source code.

## Repository Focus
Geep is planned and documented through requirements and backlog artifacts.
Primary source-of-truth areas:
- `.agents` : AI agent definitions.
- `docs/` : Domain reference material and architecture contracts.
- `backlog/` : Milestones, tasks, decisions, and completion tracking.

DO NOT change this folder hierarchy.

## Working Rules For Agents
1. **Read Before Action**
   - Review relevant files in `docs/` and `backlog/tasks/` before proposing or applying edits.
2. **Backlog Management (CRITICAL)**
   - **NEVER edit files in `backlog/tasks/`, `backlog/docs/`, or `backlog/decisions/` directly.**
   - All task operations (create, edit, status, AC) MUST use the `backlog` CLI tool.
   - Refer to `GEMINI.md` for specific command usage and instructions.
   - Always use the `--plain` flag for AI-friendly output when reading tasks.
3. **Task Workflow**
   - **Start**: Immediately set the task to "In Progress" and assign it to yourself using `backlog task edit <id> -s "In Progress" -a @agent`.
   - **Plan**: Always update the task's implementation plan (`--plan`) before coding.
   - **Progress**: Check off Acceptance Criteria (AC) and Definition of Done (DoD) items via CLI as you complete them.
   - **Complete**: Add implementation notes and a final summary via CLI before closing a task.
4. **Explicit Assumptions**
   - If information is missing, list assumptions instead of inventing silent details.

## Quality Bar
- Always be concise. Use as few words as possible.
- Clear headings and concise bullets.
- Concrete acceptance criteria where applicable.
- No ambiguous status language (use explicit states like planned, in-progress, blocked, completed).
