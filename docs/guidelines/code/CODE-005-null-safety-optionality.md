# CODE-005 Null Safety & Optionality

**Applies to:** mobile, backend, web

## Guideline

Nullability must be explicit and reflect the domain model's optionality constraints. 

- Fields nullable types: `Type?` in Kotlin, `Optional<Type>` in Java.
- Never use `null` as a sentinel value for "not applicable" — use a sealed class / enum variant instead.
- `!!` operator must be justified by a comment explaining why the value cannot be null at that point.
- Room entities: nullable columns use `Type?` in Kotlin. Room handles nullability mapping automatically.
- JSON content fields (e.g. `content` in ObservationEntity) must be nullable or have a default empty value depending on the sub-type constraints.
- Foreign keys (`sireId`, `damId`, `sourceRecordId`, `sourceFutureEventId`) must be nullable unless the domain model explicitly requires them.
