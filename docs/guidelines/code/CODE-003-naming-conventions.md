# CODE-003 Naming Conventions

**Applies to:** mobile, backend, web

## Guideline

Names must be descriptive, consistent across the codebase, and aligned with the business glossary.

### General
- Classes: PascalCase (`IndividualEntity`, `RecordDao`, `GeepDatabase`).
- Functions / methods: camelCase (`getIndividualById`, `calculateLambingDate`).
- Constants: UPPER_SNAKE_CASE for `const val` / `companion object` constants.
- Properties / variables: camelCase (`individualId`, `belongsToFlock`).
- Packages: lowercase, reverse-domain (`net.madscientists.geep.core.model`).
- Database columns: camelCase mapped by Room automatically (no `snake_case` unless schema export requires it — Room handles the mapping).

### Database Entities
- Entity class: singular, `Entity` suffix (`IndividualEntity`, `RecordEntity`).
- Tables: plural lowercase (`individuals`, `records`, `observations`).
- Foreign key columns: `<referenced_table_singular>Id` (`individualId`, `recordId`).
- Boolean columns: affirmative, no `is` prefix (`belongsToFlock` not `isBelongingToFlock`).

### Domain Model
- Match the business glossary terms exactly (e.g. `stillborn`, `officialId`, `belongsToFlock`, `colorPattern`).
- Use `FutureEvent`, `PredictedEvent`, `PlannedTask`, `WaitingDelay` consistent with domain model docs.
