# CODE-007 Database Entity & DAO Patterns

**Applies to:** mobile

## Guideline

Room DAOs must use parameterized queries, return domain types (not entities), and follow consistent naming.

### Entity Definitions
- Use `@Entity(tableName = "individuals")` with plural table names.
- Use `@PrimaryKey(autoGenerate = true)` for surrogate keys.
- Use `@ColumnInfo(name = "columnName")` when the column name differs from the property name.
- Use `@ForeignKey` only for subtype parent relationships (e.g. `ObservationEntity` → `RecordEntity` with `ON DELETE CASCADE`). Logical references (`sireId`, `damId`, `sourceRecordId`) are not enforced as Room FKs per ADR-0001.

### DAO Naming
- `getById(id)` — single item by primary key.
- `getAll()` — all items (used for sync / export).
- `getByIndividualId(individualId)` — records for a specific individual.
- `insert(entity)` / `insertAll(entities)`.
- `update(entity)`.
- `delete(entity)` / `deleteById(id)`.

### Query Rules
- All queries must use parameterized bind args (`:paramName` in Room, `PreparedStatement` on backend).
- Raw queries (`@RawQuery`) require explicit security review approval.
- Return Flow / reactive types for observable queries (LiveData in legacy, Flow preferred).
- DAOs must map entities to domain types before returning to consumers (repository pattern).
