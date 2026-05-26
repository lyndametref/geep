# FutureEvent — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. FutureEvent is a standalone entity in the Planning bounded context. Subtype-specific attributes are documented in their respective files:
> - [`predicted-event-attributes.md`](predicted-event-attributes.md) — PredictedEvent
> - [`planned-task-attributes.md`](planned-task-attributes.md) — PlannedTask
> - [`waiting-delay-attributes.md`](waiting-delay-attributes.md) — WaitingDelay

## FutureEvent (base entity)

Base entity attributes common to all FutureEvent subtypes.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | [REQ-04](../../requirements/business/REQ-04.md), [BR-010](../../business-rules/BR-010-future-event-unique-identifier.md) |
| `individualId` | Long | Yes | Logical FK to Individual. The individual this future event relates to. | [REQ-04](../../requirements/business/REQ-04.md), [BR-018](../../business-rules/BR-018-record-ownership.md) |
| `sourceRecordId` | Long | No | Logical FK to the Record (Observation or Intervention) that derived this FutureEvent. | [BR-012](../../business-rules/BR-012-lambing-prediction-based-on-mating-observation.md), [BR-022](../../business-rules/BR-022-lambing-process-flow.md), [BR-014](../../business-rules/BR-014-medication-quarantine-delay.md) |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z`. When the FutureEvent was derived. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-05.002](../../requirements/business/REQ-05.002.md) |
| `futureEventType` | Enum | Yes | Discriminator for the FutureEvent subtype. Values: `PREDICTED_EVENT`, `PLANNED_TASK`, `WAITING_DELAY`. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-13](../../requirements/business/REQ-13.md) |
