# Backlog.md CLI Reference

Companion to `.opencode/skills/backlog-md/SKILL.md` — detailed command examples, tables, and formatting guidance.

---

## Common Mistakes — Examples

### WRONG: Direct File Editing

```markdown
1. Open backlog/tasks/task-7 - Feature.md in editor
2. Change "- [ ]" to "- [x]" manually
3. Add notes or final summary directly to the file
4. Save the file
```

### CORRECT: Using CLI Commands

```bash
backlog task edit 7 --check-ac 1
backlog task edit 7 --notes "Implementation complete"
backlog task edit 7 --final-summary "PR-style summary"
backlog task edit 7 -s "In Progress" -a @agent-k
```

---

## Task Format — Modification Table

| What You Want to Change | CLI Command to Use                                       |
|-------------------------|----------------------------------------------------------|
| Title                   | `backlog task edit 42 -t "New Title"`                    |
| Status                  | `backlog task edit 42 -s "In Progress"`                  |
| Assignee                | `backlog task edit 42 -a @sara`                          |
| Labels                  | `backlog task edit 42 -l backend,api`                    |
| Description             | `backlog task edit 42 -d "New description"`              |
| Add AC                  | `backlog task edit 42 --ac "New criterion"`              |
| Add DoD                 | `backlog task edit 42 --dod "Ship notes"`                |
| Check AC #1             | `backlog task edit 42 --check-ac 1`                      |
| Check DoD #1            | `backlog task edit 42 --check-dod 1`                     |
| Uncheck AC #2           | `backlog task edit 42 --uncheck-ac 2`                    |
| Uncheck DoD #2          | `backlog task edit 42 --uncheck-dod 2`                   |
| Remove AC #3            | `backlog task edit 42 --remove-ac 3`                     |
| Remove DoD #3           | `backlog task edit 42 --remove-dod 3`                    |
| Add Plan                | `backlog task edit 42 --plan "1. Step one\n2. Step two"` |
| Add Notes (replace)     | `backlog task edit 42 --notes "What I did"`              |
| Append Notes            | `backlog task edit 42 --append-notes "Another note"`     |
| Add Final Summary       | `backlog task edit 42 --final-summary "PR-style summary"` |
| Append Final Summary    | `backlog task edit 42 --append-final-summary "Another detail"` |
| Clear Final Summary     | `backlog task edit 42 --clear-final-summary`             |

---

## Creating Tasks — Examples

```bash
backlog task create "Task title" -d "Description" --ac "First criterion" --ac "Second criterion"
```

---

## Acceptance Criteria Management — Examples

```bash
# Add new criteria (MULTIPLE values allowed)
backlog task edit 42 --ac "User can login" --ac "Session persists"

# Check specific criteria by index (MULTIPLE values supported)
backlog task edit 42 --check-ac 1 --check-ac 2 --check-ac 3

# Mixed operations in single command
backlog task edit 42 --check-ac 1 --uncheck-ac 2 --remove-ac 3

# WRONG formats that don't work:
# backlog task edit 42 --check-ac 1,2,3  # No comma-separated values
# backlog task edit 42 --check-ac 1-3    # No ranges
# backlog task edit 42 --check 1         # Wrong flag name

# Multiple operations of same type
backlog task edit 42 --uncheck-ac 1 --uncheck-ac 2
backlog task edit 42 --remove-ac 2 --remove-ac 4
```

---

## Definition of Done — Examples

```bash
# Add DoD items (MULTIPLE values allowed)
backlog task edit 42 --dod "Run tests" --dod "Update docs"

# Check/uncheck DoD items by index
backlog task edit 42 --check-dod 1 --check-dod 2
backlog task edit 42 --uncheck-dod 1

# Remove DoD items by index
backlog task edit 42 --remove-dod 2

# Create without defaults
backlog task create "Feature" --no-dod-defaults
```

---

## Implementing Tasks — Examples

### Start work

```bash
backlog task edit 42 -s "In Progress" -a @{myself}
```

### Add implementation plan

```bash
backlog task edit 42 --plan "1. Research codebase for references\n2. Research on internet for similar cases\n3. Implement\n4. Test"
```

### Add implementation notes

```bash
backlog task edit 42 --append-notes "Investigated root cause" --append-notes "Added tests for edge case"
backlog task edit 42 --notes "Initial implementation done; pending integration tests"
```

### Add final summary

```bash
backlog task edit 42 --final-summary "Implemented pattern X because Reason Y; updated files Z and W; added tests"
```

---

## Typical Workflow — Full Script

```bash
# 1. Identify work
backlog task list -s "To Do" --plain

# 2. Read task details
backlog task 42 --plain

# 3. Start work: assign yourself & change status
backlog task edit 42 -s "In Progress" -a @myself

# 4. Add implementation plan
backlog task edit 42 --plan "1. Analyze\n2. Refactor\n3. Test"

# 5. Share the plan with the user and wait for approval

# 6. Work on the task (write code, test, etc.)

# 7. Mark acceptance criteria as complete
backlog task edit 42 --check-ac 1 --check-ac 2 --check-ac 3

# 8. Add Final Summary
backlog task edit 42 --final-summary "Refactored using strategy pattern, updated tests"

# 9. Mark task as done
backlog task edit 42 -s Done
```

---

## Searching Tasks — Examples

```bash
# Search for tasks about authentication
backlog search "auth" --plain

# Search only in tasks (not docs/decisions)
backlog search "login" --type task --plain

# Search with filters
backlog search "api" --status "In Progress" --plain
backlog search "bug" --priority high --plain

# Find tasks that modified a project file path
backlog search --modified-file src/server/api.ts --plain
```

---

## Complete CLI Command Reference

### Task Creation

| Action | Command |
|--------|---------|
| Create task | `backlog task create "Title"` |
| With description | `backlog task create "Title" -d "Description"` |
| With AC | `backlog task create "Title" --ac "Criterion 1" --ac "Criterion 2"` |
| With final summary | `backlog task create "Title" --final-summary "PR-style summary"` |
| With references | `backlog task create "Title" --ref src/api.ts --ref https://...` |
| With documentation | `backlog task create "Title" --doc https://design-docs.example.com` |
| With modified files | `backlog task create "Title" --modified-file src/api.ts --modified-file src/ui.ts` |
| With all options | `backlog task create "Title" -d "Desc" -a @sara -s "To Do" -l auth --priority high --ref src/api.ts --doc docs/spec.md --modified-file src/api.ts` |
| Create draft | `backlog task create "Title" --draft` |
| Create subtask | `backlog task create "Title" -p 42` |

### Task Modification

| Action | Command |
|--------|---------|
| Edit title | `backlog task edit 42 -t "New Title"` |
| Edit description | `backlog task edit 42 -d "New description"` |
| Change status | `backlog task edit 42 -s "In Progress"` |
| Assign | `backlog task edit 42 -a @sara` |
| Add labels | `backlog task edit 42 -l backend,api` |
| Set priority | `backlog task edit 42 --priority high` |

### Acceptance Criteria

| Action | Command |
|--------|---------|
| Add AC | `backlog task edit 42 --ac "New" --ac "Another"` |
| Remove AC #2 | `backlog task edit 42 --remove-ac 2` |
| Remove multiple ACs | `backlog task edit 42 --remove-ac 2 --remove-ac 4` |
| Check AC #1 | `backlog task edit 42 --check-ac 1` |
| Check multiple ACs | `backlog task edit 42 --check-ac 1 --check-ac 3` |
| Uncheck AC #3 | `backlog task edit 42 --uncheck-ac 3` |
| Mixed | `backlog task edit 42 --check-ac 1 --uncheck-ac 2 --remove-ac 3 --ac "New"` |

### Task Content

| Action | Command |
|--------|---------|
| Add plan | `backlog task edit 42 --plan "1. Step one\n2. Step two"` |
| Add notes | `backlog task edit 42 --notes "Implementation details"` |
| Add final summary | `backlog task edit 42 --final-summary "PR-style summary"` |
| Append final summary | `backlog task edit 42 --append-final-summary "More details"` |
| Clear final summary | `backlog task edit 42 --clear-final-summary` |
| Add dependencies | `backlog task edit 42 --dep task-1 --dep task-2` |
| Add references | `backlog task edit 42 --ref src/api.ts --ref https://...` |
| Add documentation | `backlog task edit 42 --doc https://... --doc docs/spec.md` |
| Set modified files | `backlog task edit 42 --modified-file src/api.ts --modified-file src/ui.ts` |

### Task Operations

| Action | Command |
|--------|---------|
| View task | `backlog task 42 --plain` |
| List tasks | `backlog task list --plain` |
| Search tasks | `backlog search "topic" --plain` |
| Search with filter | `backlog search "api" --status "To Do" --plain` |
| Search by modified file | `backlog search --modified-file src/api.ts --plain` |
| Filter by status | `backlog task list -s "In Progress" --plain` |
| Filter by assignee | `backlog task list -a @sara --plain` |
| Archive task | `backlog task archive 42` |
| Demote to draft | `backlog task demote 42` |

---

## Multi-line Input

The CLI preserves input literally — shells do not convert `\n` inside normal quotes.

**1. Repeat `--append-*` for each line (works everywhere):**

```bash
backlog task edit 42 --notes "First line"
backlog task edit 42 --append-notes "Second line"
backlog task edit 42 --append-notes "Third line"
```

**2. Real newlines inside double quotes:**

```bash
backlog task edit 42 --notes "First line
Second line

Final paragraph"
```

Works for `--desc`, `--plan`, `--final-summary`, and `--append-*` variants.

**3. Shell-specific (some sandboxes reject these):**

```bash
# Bash/Zsh (ANSI-C quoting)
backlog task edit 42 --notes $'Line1\nLine2'

# POSIX sh
backlog task edit 42 --notes "$(printf 'Line1\nLine2')"
```

Do not expect the literal sequence `\n` inside double quotes to become a newline.

---

## Implementation Notes Formatting

- Keep concise and time-ordered; focus on progress, decisions, and blockers.
- Use short paragraphs or bullet lists.
- Repeat `--append-notes` for each line or use real newlines:

```bash
backlog task edit 42 --append-notes "- Added new API endpoint" \
  --append-notes "- Updated tests" \
  --append-notes "- TODO: monitor staging deploy"
```

Or:

```bash
backlog task edit 42 --append-notes "- Added new API endpoint
- Updated tests
- TODO: monitor staging deploy"
```

---

## Final Summary Formatting

Treat as a PR description: outcome first, then key changes and tests.

```text
Added Final Summary support across CLI/MCP/Web/TUI to separate PR summaries from progress notes.

Changes:
- Added `finalSummary` to task types and markdown section parsing/serialization.
- CLI/MCP/Web/TUI now render and edit Final Summary.

Tests:
- bun test src/test/final-summary.test.ts
```

Cover: **what changed**, **why**, **user impact**, **tests run**, **risks/follow-ups**.

---

## Task Images

- Store images under `backlog/assets/` (e.g., `backlog/assets/images/screenshot.png`)
- Supported formats: png, jpg, jpeg, gif, svg, webp, avif
- Markdown: `![example](assets/images/screenshot.png)`
- Path starts with `assets/` (not the backlog directory name)

---

## Document Management

### CLI

```bash
# Create
backlog doc create "API Guidelines"
backlog doc create "Setup Guide" -p guides/setup
backlog doc create "Architecture" -t guide

# Update
backlog doc update doc-1 --content "Updated markdown"
backlog doc update doc-1 --title "Setup Handbook" -t guide --tags setup,runbook -p guides

# List / View
backlog doc list
backlog doc view doc-1
```

### MCP/API

- `document_create` / `document_update` for programmatic access.
- Paths are relative to `backlog/docs/`; absolute paths and `..` are rejected.
- Types: `readme`, `guide`, `specification`, `other`.

---

## Common Issues

| Problem | Solution |
|---------|----------|
| Task not found | `backlog task list --plain` to check ID |
| AC won't check | `backlog task 42 --plain` to see correct AC numbers |
| Changes not saving | Use CLI, not direct file editing |
| Metadata out of sync | `backlog task edit 42 -s <current-status>` |
