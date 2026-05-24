# Delegate Batch-Entry to UI Layer — Replace Many-to-Many Join Table with Direct Individual Reference on Records

## Status

Accepted

## Decision

Use the second option and create `long individualId` directly in the `records` table as a required foreign key to `individuals`.

Batch-entry is delegated to the UI layer. When the user selects multiple individuals and submits a single observation or intervention:

- The UI iterates over the selected individual IDs.
- For each individual, it creates one `Record` (with the corresponding `individualId`) and one subtype row (`Observation` or `Intervention`).
- The content fields (observation content, intervention content) are duplicated across the N records — this is an accepted trade-off.

The result is a strictly one-to-many relationship: one individual can have many records, but each record belongs to exactly one individual.

## Context

The original domain model (docs/domain-model/business-object-model.md) specifies that "a record can appear in the journal of one or more individuals so batch capture is represented without losing per-individual chronology" (line 118). This business rule supports REQ-04.002 (batch entry for observations) and REQ-13.002 (batch entry for interventions), both of which require that a single observation or intervention can be applied to multiple selected individuals in one operation.

One option is to implement this requirement with a many-to-many join table:

- `individual_record_cross_ref` — linking `individualId` and `recordId` with a composite primary key and foreign keys to both `individuals` and `records`.

This approach has several drawbacks:

1. **Schema complexity** — an extra table, extra DAO, extra entity class, and extra joins for every record query.
2. **Unclear ownership** — a record that belongs to multiple individuals has ambiguous semantics: is it "one observation shared by N individuals" or "N individual observations stored together"? The cross-ref obscures this.
3. **Premature optimization** — the batch-entry requirement (REQ-04.002, REQ-13.002) can be satisfied at the UI layer without encoding the many-to-many relationship in the data model.

A secon option is to use a direct foreign key in the record table and put `individualId` directly on `records`, making the relationship one-to-many (one individual owns one record). Batch-entry becomes a UI concern: the UI iterates over selected individuals and creates one record per individual. Advantages: simpler schema, fewer joins, explicit ownership semantics.

## Consequences

**Positive:**

- Simpler database schema — one fewer table, one fewer DAO, one fewer entity class.
- Fewer joins in all record queries — `records` already has `individualId` as a direct column.
- Explicit ownership semantics — each record belongs to exactly one individual, no ambiguity.
- Straightforward data insertion — a single `INSERT INTO records` per record, no cross-ref maintenance.
- Easier pagination and sorting — record queries per individual are simple filtered queries with no join.
- Room foreign-key management is simpler — no need to manage composite FK constraints.
- Indexing is simpler — a single index on `records(individualId)` suffices.

**Negative:**

- Data duplication — the same observation/intervention content is stored N times when applied to N individuals. For typical batch sizes (tens of animals) this is negligible in storage cost.
- Content-update complexity — if shared content needs to be edited retroactively, N rows must be updated instead of one cross-ref entry. This is acceptable because no such requirement currently exists (speculative).
- Querying "all records that were batch-entered together" now requires grouping on content columns or a batch-intent identifier, rather than a simple cross-ref join. If this query becomes necessary, a `batchId` column on `records` can be added later. At the time of the decision this is not a identified requirement.
 