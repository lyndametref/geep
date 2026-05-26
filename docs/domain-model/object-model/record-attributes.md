# Record — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Record is the shared journal entry supertype. Subtype-specific attributes are documented in their respective files:
> - [`observation-attributes.md`](observation-attributes.md) — Observation
> - [`intervention-attributes.md`](intervention-attributes.md) — Intervention

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | [BR-009](../../business-rules/BR-009-record-unique-identifier.md) |
| `recordType` | Enum | Yes | Values: `OBSERVATION`, `INTERVENTION`. Discriminator for the journal entry subtype. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-13](../../requirements/business/REQ-13.md), [BR-009](../../business-rules/BR-009-record-unique-identifier.md) |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Used for chronological journal ordering. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-13](../../requirements/business/REQ-13.md), [REQ-04.006](../../requirements/business/REQ-04.006.md) |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set ([BR-018](../../business-rules/BR-018-record-ownership.md)). A record belongs to exactly one individual. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-13](../../requirements/business/REQ-13.md), [BR-018](../../business-rules/BR-018-record-ownership.md), [ADR-0001](../../architecture/adr/ADR-0001-batch-entry-delegated-to-ui.md) |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event ([BR-015](../../business-rules/BR-015-future-event-realization.md)). | [BR-015](../../business-rules/BR-015-future-event-realization.md) |
