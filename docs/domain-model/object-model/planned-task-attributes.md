# PlannedTask — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. PlannedTask is a specialization of FutureEvent. Attributes marked as inherited from FutureEvent are documented in full in `future-event-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from FutureEvent (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | REQ-04, BR-010 |
| `individualId` | Long | Yes | Logical FK to Individual. | REQ-04, BR-018 |
| `sourceRecordId` | Long | No | Logical FK to the Record that derived this PlannedTask. | BR-012, BR-013, BR-014 |
  | `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp. When the PlannedTask was derived or manually created. | REQ-04, REQ-05.002 |
| `futureEventType` | Enum | Yes | Fixed to `PLANNED_TASK` for PlannedTask instances. | REQ-04, REQ-13 |

## PlannedTask-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `status` | Enum (TaskStatus) | Yes | Lifecycle status. Values: `PENDING` (awaiting completion), `DONE` (completed), `CANCELLED` (no longer required). | REQ-04, REQ-13, BR-015 |
| `reminderDate` | Timestamp (UTC) | No | Date when a notification reminder should be shown. May be before `dueDate`. | BR-013 |
| `dueDate` | Timestamp (UTC) | Yes | Date by which the task should be completed (e.g., weaning date = birth date + 3 months). | REQ-04.005, BR-013 |
| `content` | JSON (Structured Data) | Yes | Type-specific task payload. Contains a `title` field and other task-specific data such as `assignedTo`, `description`. | REQ-04.005, BR-013 |
