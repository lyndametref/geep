# Record — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Record is the shared journal entry supertype. Subtype-specific attributes are documented in their respective files:
> - [`observation-attributes.md`](observation-attributes.md) — Observation
> - [`intervention-attributes.md`](intervention-attributes.md) — Intervention

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | BR-009 |
| `recordType` | Enum | Yes | Values: `OBSERVATION`, `INTERVENTION`. Discriminator for the journal entry subtype. | REQ-04, REQ-13, BR-009 |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Used for chronological journal ordering. | REQ-04, REQ-13, REQ-04.006 |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set (BR-018). A record belongs to exactly one individual. | REQ-04, REQ-13, BR-018, ADR-0001 |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event (BR-015). | BR-015 |
