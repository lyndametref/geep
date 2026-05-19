---
name: architecture-decision-record
description: 'Create and manage Architecture Decision Records (ADRs) in the architecture documentation folder.'
argument-hint: 'ADR title or description of the architectural decision to record.'
user-invocable: true
disable-model-invocation: false
---

# Architecture Decision Record (ADR) Management

## Outcome
Create, revise, and manage Architecture Decision Records (ADRs), stored in `docs/architecture/adr`. Once an ADR reaches a final status (Accepted, Refused), its body becomes immutable — only status transitions to Deprecated or Superseded are permitted.

## When To Use
- Record a significant architectural decision with rationale
- Propose a new architectural approach for discussion
- Supersede or deprecate a previously accepted/refused decision
- Document the context, trade-offs, and consequences of a decision

Do not use this skill for:
- Business or project management decisions (use `backlog/decisions/`)
- Implementation notes or task-level choices
- Casual discussions that do not impact architecture

## ADR Location
- All ADRs live in `docs/architecture/`
- File naming convention: `ADR-<NNNN>-<kebab-case-title>.md`
  - Example: `ADR-0001-use-neo4j-for-genealogy.md`
- A running index is maintained in `docs/architecture/ADR-INDEX.md`

## ADR Format (Mandatory Template)
Every ADR MUST follow this structure. See [the example](references/example-ADR-0001-use-neo4j-for-genealogy.md) for a complete worked example.

```markdown
# <Title>

## Status

<Proposed | Accepted | Refused | Deprecated | Superseded>

## Context

<Describe the problem, forces at play, background, and options considered.>

## Decision

<State the decision clearly. If superseding, reference the superseded ADR.>

## Consequences

<List positive and negative consequences, trade-offs, and migration notes.>
```

- **Status** values and their lifecycle:
  - `Proposed` — initial state, open for discussion
  - `Accepted` — decision adopted (immutable body)
  - `Refused` — decision rejected (immutable body)
  - `Deprecated` — still valid but no longer recommended
  - `Superseded` — replaced by another ADR (must reference the new ADR)
- Status transitions allowed:
  - `Proposed` → `Accepted` | `Refused`
  - `Accepted` → `Deprecated` | `Superseded`
  - `Refused` → `Deprecated` | `Superseded`
  - `Deprecated` → `Superseded`
- Any other status change is prohibited without creating a new ADR.

## Procedure

### 1. Create a new ADR (Proposed)

1. **Determine the next ADR number** — find the highest existing `ADR-NNNN-` prefix in `docs/architecture/`, increment by 1.
2. **Define the title** — short, descriptive noun phrase (e.g., "Use Neo4j for Genealogy").
3. **Write the ADR** using the template above with status `Proposed`.
4. **Save** to `docs/architecture/ADR-<NNNN>-<kebab-case-title>.md`.
5. **Update the index** — add an entry to `docs/architecture/ADR-INDEX.md`.

### 2. Accept or Refuse a Proposed ADR

1. **Validate** that current status is `Proposed`.
2. **Change status** to `Accepted` or `Refused`.
3. **Immutable lock** — once set to `Accepted` or `Refused`, the body (Context, Decision, Consequences) MUST NOT be edited further.

### 3. Supersede a Final ADR

1. **Validate** that current status is `Accepted` or `Refused`.
2. ** Supersed**: create a new ADR that replaces the old one. The old ADR's `Status` section MUST reference the new ADR number (`Superseded by ADR-NNNN`). The new ADR's `Context` MUST reference the old ADR (`Supersedes ADR-NNNN`).
3. **Change status** of the old ADR to `Superseded`.
4. **Update the index** entry status.
5. Only status is editable — body remains frozen.

### 3. Deprecate an ADR

1. **Validate** that current status is `Proposed`, `Accepted` or `Refused`.
2. **Change status** of the ADR to `Deprecated` and provide rationale in the `status` section (e.g., "Deprecated due to change in REQ-01.003" or "Deprecated because of new architectural guidelines").
3. **Update the index** entry status.
4. Only status is editable — body remains frozen.

### 4. Revise a Proposed ADR (Before Final Status)

1. Any field (Context, Decision, Consequences, Status) may be edited freely as long as the status is `Proposed`.
2. Update the index if the title or status changed.

## Immutability Rules

| Status | Body Editable | Status Editable To |
|--------|:---:|:---:|
| Proposed | Yes | Accepted, Refused |
| Accepted | **No** | Deprecated, Superseded |
| Refused | **No** | Deprecated, Superseded |
| Deprecated | **No** | — |
| Superseded | **No** | — |

- "Body" = the `## Context`, `## Decision`, and `## Consequences` sections.
- The `## Status` line is always editable (within allowed transitions).
- The `# Title` line is editable only while status is `Proposed`.

## Completion Checks
- [ ] ADR file exists at `docs/architecture/adr/ADR-<NNNN>-<kebab-case-title>.md`
- [ ] Template follows the defined format with all required sections
- [ ] Status is valid and transitions are legal
- [ ] Index (`docs/architecture/adr/ADR-INDEX.md`) is updated
- [ ] If superseding, the old ADR links to the new one and vice versa

## Output Template
After any operation, summarize:

- **Action**: create / accept / refuse / deprecate / supersede
- **ADR**: `ADR-NNNN` – Title
- **Status**: OldStatus → NewStatus
- **File**: `docs/architecture/adr/ADR-NNNN-title.md`
- **Transitions validated**: yes/no
- **Index updated**: yes/no
