# Genealogy display label

## Priority

The display label for an individual node in the genealogy view uses the best available identifier in the following priority:

1. **name** — if not null and not blank
2. **officialId** — if not null and not blank
3. **Generated fallback** — `#<id>` where `id` is the immutable internal identifier (BR-001)

## Computation

The display label is computed at render time. It is not stored as a separate field.

## Scope

This label is used in the genealogy view (REQ-02.002). Other views may use a different label resolution strategy.
