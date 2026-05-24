# WaitingDelay — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. WaitingDelay is a specialization of FutureEvent. Attributes marked as inherited from FutureEvent are documented in full in `future-event-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from FutureEvent (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | REQ-04, BR-016 |
| `individualId` | Long | Yes | Logical FK to Individual. | REQ-04, BR-018 |
| `sourceRecordId` | Long | No | Logical FK to the Record that derived this WaitingDelay. In this case the source record Id is mandatory. | BR-012, BR-013, BR-014 |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending. When the WaitingDelay was derived or created. | REQ-04, REQ-05.002, BR-016 |
| `futureEventType` | Enum | Yes | Fixed to `WAITING_DELAY` for WaitingDelay instances. | BR-010 |

## WaitingDelay-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `status` | Enum (DelayStatus) | Yes | Lifecycle status. Values: `WAITING` (delay period in progress), `ELAPSED` (period over), `ABORTED` (delay cancelled). | BR-010, BR-015 |
| `delayElapsedAt` | Timestamp (UTC) | Yes | Date when the delay period ends. For example, calculated from treatment date + product-specific withdrawal duration. | REQ-13.004, BR-014 |
| `content` | JSON (Structured Data) | No | Type-specific metadata about the waiting period for example references to the associated treatment product, medication details, and withdrawal period specifications. | BR-007, BR-014 |
