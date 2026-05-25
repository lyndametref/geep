# FutureEvent — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. FutureEvent is a standalone entity in the Planning bounded context. Subtype-specific attributes are documented in their respective files:
> - [`predicted-event-attributes.md`](predicted-event-attributes.md) — PredictedEvent
> - [`planned-task-attributes.md`](planned-task-attributes.md) — PlannedTask
> - [`waiting-delay-attributes.md`](waiting-delay-attributes.md) — WaitingDelay

## FutureEvent (base entity)

Base entity attributes common to all FutureEvent subtypes.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | REQ-04, BR-010 |
| `individualId` | Long | Yes | Logical FK to Individual. The individual this future event relates to. | REQ-04, BR-018 |
| `sourceRecordId` | Long | No | Logical FK to the Record (Observation or Intervention) that derived this FutureEvent. | BR-012, BR-013, BR-014 |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z`. When the FutureEvent was derived. | REQ-04, REQ-05.002 |
| `futureEventType` | Enum | Yes | Discriminator for the FutureEvent subtype. Values: `PREDICTED_EVENT`, `PLANNED_TASK`, `WAITING_DELAY`. | REQ-04, REQ-13 |
