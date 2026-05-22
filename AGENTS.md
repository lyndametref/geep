# AGENTS

## Purpose
This file defines how coding and documentation agents should operate in this repository.

## Project Structure

This single repository will contain all the applications for the Geep ecosystem as well as the backlog and the requirements.

Here is the structure:
- `backlog/` : Tasks, project-specific docs, and decisions.
- `docs/` : Project documentation. Contains all documentation about requirements, architecture, domain model and technical specification.
  - `docs/architecture/**.md`: architecture documentation of the whole ecosystem
  - `docs/businessRules/**.md` : business rules used in the ovin flock management
  - `docs/domainModel/**.md` : domain model for the ovin flock management
  - `docs/requirements/**.md` : Business requirements for the ovin flock management tool. No technical requirements allowed here.
  - `docs/specs/**.md` : technical specification of the ovin flock management tool.
- `apps/` : Contains all application source code.
  - `apps/appAndroid/` : Android application source code.
- `.ai` : source of truth of ai tools configuration
  - `.ai/agents`: AI agents definitions
  - `.ai/skills`: AI skills definitions

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

## Coding rules
- never use timestamps without timezone. 

## Backlog.md Skill

Detailed backlog.md CLI usage instructions are available in the skill at `.opencode/skills/backlog-md/SKILL.md`.
Load it with the `skill` tool when you need to perform backlog operations.
