# CODE-010 State Handling & Status Patterns

**Applies to:** mobile, backend

## Guideline

Domain object states must be modeled as sealed types with explicit, valid transitions. State is derived where possible, stored only when necessary.
