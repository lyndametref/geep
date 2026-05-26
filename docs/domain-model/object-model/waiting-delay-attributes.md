# WaitingDelay — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. WaitingDelay is a specialization of FutureEvent. Attributes marked as inherited from FutureEvent are documented in full in `future-event-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from FutureEvent (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | [REQ-04](../../requirements/business/REQ-04.md), [BR-010](../../business-rules/BR-010-future-event-unique-identifier.md) |
| `individualId` | Long | Yes | Logical FK to Individual. | [REQ-04](../../requirements/business/REQ-04.md), [BR-018](../../business-rules/BR-018-record-ownership.md) |
| `sourceRecordId` | Long | No | Logical FK to the Record that derived this WaitingDelay. In this case the source record Id is mandatory. | [BR-012](../../business-rules/BR-012-lambing-prediction-based-on-mating-observation.md), [BR-022](../../business-rules/BR-022-lambing-process-flow.md), [BR-014](../../business-rules/BR-014-medication-quarantine-delay.md) |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending. When the WaitingDelay was derived or created. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-05.002](../../requirements/business/REQ-05.002.md) |
| `futureEventType` | Enum | Yes | Fixed to `WAITING_DELAY` for WaitingDelay instances. | [REQ-13.004](../../requirements/business/REQ-13.004.md) |

## WaitingDelay-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `status` | Enum (DelayStatus) | Yes | Lifecycle status. Values: `WAITING` (delay period in progress), `ELAPSED` (period over), `ABORTED` (delay cancelled). | [REQ-13.004](../../requirements/business/REQ-13.004.md), [BR-015](../../business-rules/BR-015-future-event-realization.md) |
| `delayElapsedAt` | Timestamp (UTC) | Yes | Date when the delay period ends. For example, calculated from medication date + product-specific withdrawal duration. | [REQ-13.004](../../requirements/business/REQ-13.004.md), [BR-014](../../business-rules/BR-014-medication-quarantine-delay.md) |
| `content` | JSON (Structured Data) | No | Type-specific metadata about the waiting period for example references to the associated medication product, its details, and withdrawal period specifications. | [BR-007](../../business-rules/BR-007-quarantine-period-representation.md), [BR-014](../../business-rules/BR-014-medication-quarantine-delay.md) |
