# Derive Living State from Death Date

## Decision

The living state of an individual sheep is derived from the presence or absence of a death date. No separate living status or alive indicator field is stored.

- If no death date is recorded → the individual is alive.
- If a death date is recorded → the individual is dead.

## Status

Accepted

## Context

In flock management, we need to determine whether an individual sheep is alive or dead for filtering, reporting, and lifecycle-aware business rules. Two approaches were considered:

1. **Separate living-status field** — a boolean or enum stored as a column on the individual record. When death is recorded, the application must update both the death date and the status field.

2. **Derive from death date** — only the death date field is stored. Living state is computed from its presence: no date means alive, a date means dead.

Option 2 (derive from death date) was chosen.

Key considerations:

- A separate status field introduces a synchronization risk: the death date and the living status could disagree (e.g. a date is present but the status says alive, or vice versa). This requires application-level invariants or database constraints to keep them in sync.
- The derived approach has a single source of truth: the death date. There is no second field to maintain.
- Querying for alive/dead individuals is straightforward: `IS NULL` for alive, `IS NOT NULL` for dead.
- The derived approach does not preclude adding a computed or indexed column later if query performance requires it.

## Consequences

**Positive:**

- **Simpler data model** — one fewer column, no enum or boolean type to manage.
- **No synchronization risk** — the death date is the single source of truth for living state; no pair of fields can drift out of agreement.
- **Clear semantics** — death date presence has an unambiguous meaning; a separate status field invites interpretation questions (e.g. "can an individual be alive with a death date?").
- **Simpler data entry** — recording a death is a single field update, not a two-field operation.

**Negative:**

- **Derived state in queries** — every alive/dead filter must use `IS NULL` / `IS NOT NULL` rather than a direct column comparison. This is slightly less readable than a direct boolean check.
- **No explicit "unknown" or "other" state** — if the domain later requires intermediate states (e.g. missing/presumed dead, sold/slaughtered distinction), a nullable date alone cannot express it. That would require either a status enum or additional date fields.
- **Indexing** — filtering on the nullability of the death date column can use an index, but the selectivity may differ from a dedicated boolean index.
- **ORM mapping** — the application layer must consistently map the derived state (e.g. a computed property in the entity/model class) rather than reading a stored column.
