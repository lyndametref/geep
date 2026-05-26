# Observation — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Observation is a specialization of Record. Attributes marked as inherited from Record are documented in full in `record-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from Record (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | [BR-009](../../business-rules/BR-009-record-unique-identifier.md) |
| `recordType` | Enum | Yes | Fixed to `OBSERVATION` for Observation instances. Discriminator for the journal entry subtype. | [REQ-04](../../requirements/business/REQ-04.md), [BR-009](../../business-rules/BR-009-record-unique-identifier.md) |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Represents when the record was logged in the system. Used for chronological journal ordering. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-04.006](../../requirements/business/REQ-04.006.md) |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set ([BR-018](../../business-rules/BR-018-record-ownership.md)). A record belongs to exactly one individual. | [REQ-04](../../requirements/business/REQ-04.md), [BR-018](../../business-rules/BR-018-record-ownership.md), [ADR-0001](../../architecture/adr/ADR-0001-batch-entry-delegated-to-ui.md) |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event ([BR-015](../../business-rules/BR-015-future-event-realization.md)). | [BR-015](../../business-rules/BR-015-future-event-realization.md) |

## Observation-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp (e.g. `2024-06-01T10:00:00Z`). Represents when the observation actually occurred, which may differ from `recordedAt` (the log time). | [REQ-04](../../requirements/business/REQ-04.md) |
| `content` | JSON (Structured Data) | Yes | UTF-8 encoded JSON string. Payload structure defined in `docs/specs/data-types/observation-content-schema.json`. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-04.001](../../requirements/business/REQ-04.001.md), [REQ-04.007](../../requirements/business/REQ-04.007.md), [REQ-15.001](../../requirements/business/REQ-15.001.md), [BR-019](../../business-rules/BR-019-flock-membership-tracking.md) |

