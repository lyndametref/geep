# PlannedTask — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. PlannedTask is a specialization of FutureEvent. Attributes marked as inherited from FutureEvent are documented in full in `future-event-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from FutureEvent (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | [REQ-04](../../requirements/business/REQ-04.md), [BR-010](../../business-rules/BR-010-future-event-unique-identifier.md) |
| `individualId` | Long | Yes | Logical FK to Individual. | [REQ-04](../../requirements/business/REQ-04.md), [BR-018](../../business-rules/BR-018-record-ownership.md) |
| `sourceRecordId` | Long | No | Logical FK to the Record that derived this PlannedTask. | [BR-012](../../business-rules/BR-012-lambing-prediction-based-on-mating-observation.md), [BR-022](../../business-rules/BR-022-lambing-process-flow.md), [BR-014](../../business-rules/BR-014-medication-quarantine-delay.md) |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp. When the PlannedTask was derived or manually created. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-05.002](../../requirements/business/REQ-05.002.md) |
| `futureEventType` | Enum | Yes | Fixed to `PLANNED_TASK` for PlannedTask instances. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-13](../../requirements/business/REQ-13.md) |

## PlannedTask-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `status` | Enum (TaskStatus) | Yes | Lifecycle status. Values: `PENDING` (awaiting completion), `DONE` (completed), `CANCELLED` (no longer required). | [REQ-04](../../requirements/business/REQ-04.md), [REQ-13](../../requirements/business/REQ-13.md), [BR-015](../../business-rules/BR-015-future-event-realization.md) |
| `reminderDate` | Timestamp (UTC) | No | Date when a notification reminder should be shown. May be before `dueDate`. | [BR-022](../../business-rules/BR-022-lambing-process-flow.md) |
| `dueDate` | Timestamp (UTC) | Yes | Date by which the task should be completed (e.g., weaning date = birth date + 3 months). | [REQ-04.005](../../requirements/business/REQ-04.005.md), BR-013 |
| `content` | JSON (Structured Data) | Yes | Type-specific task payload. Contains a `title` field and other task-specific data such as `assignedTo`, `description`. | [REQ-04.005](../../requirements/business/REQ-04.005.md), BR-013 |
