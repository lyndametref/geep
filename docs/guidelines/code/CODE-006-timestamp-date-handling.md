# CODE-006 Timestamp & Date Handling

**Applies to:** mobile, backend, web

## Guideline

All timestamps must include timezone information. Use UTC as the canonical timezone for storage and transmission.

- Kotlin: use `java.time.Instant` for timestamps (UTC). Use `java.time.LocalDate` for calendar dates (no time, no timezone).
- Room: use `TypeConverter` to convert `Instant` ↔ `String` and `LocalDate` ↔ `String` (see existing `Converters.kt`).
- Backend: use `Instant` (Java) / `OffsetDateTime` with UTC in PostgreSQL `TIMESTAMP WITH TIME ZONE` columns.
- Web: use UTC ISO-8601 strings in API payloads. Convert to local timezone only for display, not for storage.
- Display: convert UTC to the user's local timezone at the presentation layer only.
- Timezone conversion must be explicit — never rely on system default timezone implicitly.
