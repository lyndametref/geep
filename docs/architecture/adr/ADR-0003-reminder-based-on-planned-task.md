# Reminders for Routine Care Based on PlannedTask

## Decision

Routine care reminders are implemented by creating PlannedTask records with a `reminderDate` set before the `dueDate`. The PlannedTask entity serves as the vehicle for both the task itself and its reminder — no separate Reminder entity is introduced.

- A routine care reminder is a PlannedTask where `reminderDate` is populated (must be earlier or equal to `dueDate`).
- The calendar view renders these PlannedTasks.
- Notification triggering (if implemented later) will read the `reminderDate` field on PlannedTask to determine when to fire.
- New routine care types (e.g., hoof trimming, shearing) are added as PlannedTask instances , not as new entities.

## Status

Proposed

## Context

REQ-13.006 requires the system to support reminders for routine care interventions such as hoof trimming and shearing. The acceptance criteria call out these two specific types, and the requirement states that new reminder types can be added as the product evolves.

The following domain model landscape already exists:

1. **PlannedTask entity** — A specialization of FutureEvent in the Planning bounded context (`docs/domain-model/object-model/business-object-model.md`). It already carries:
   - `reminderDate` (optional timestamp) — date when a notification reminder should be shown
   - `dueDate` (required timestamp) — date by which the task should be completed
   - `content` (JSON) — task payload including a `title` field and other task-specific data
   - `status` (enum: PENDING, DONE, CANCELLED)
   - `individualId` and `sourceRecordId`

2. **Calendar view** (`docs/specs/calendar-view.md`) — renders PlannedTask items on their `dueDate` as "Task due" events, and supports a "Reminders" filter category. The calendar is the primary user-facing display for upcoming care actions.

Two approaches were considered:

- **Option A — New Reminder entity**: Create a separate `Reminder` entity with its own attributes (trigger date, target intervention type, recurrence rule, dismissal state). This would parallel PlannedTask but be dedicated to reminders only.

- **Option B — PlannedTask as reminder vehicle** (chosen): Reuse PlannedTask for routine care reminders. The existing `reminderDate` field indicates when the user should be reminded; the calendar surfaces these items. This avoids entity proliferation and leverages the existing infrastructure.

Option B was chosen because:

- The domain concept of a "reminder for routine care" is semantically a planned task — something that must be done by a certain date, with an optional early notice.
- The calendar view renders PlannedTask items; adding reminder-specific styling (distinct color, filter category) is a UI concern, not a data model change.
- A separate Reminder entity would share most attributes with PlannedTask, indicating model duplication.

## Consequences

**Positive:**

- **Simpler data model** — no new entity, no new table, no new DAO. The existing PlannedTask schema covers the requirement as-is.
- **Single source of truth** — a routine care action and its reminder are the same record. There is no risk of a PlannedTask existing without its corresponding Reminder or vice versa.
- **Calendar integration for free** — the existing calendar rendering pipeline already handles PlannedTask display, filtering, and per-individual drill-down.
- **Extensibility** — new reminder types (e.g., deworming, vaccination) are added by creating new PlannedTask instances with appropriate `content` payloads. No code changes beyond the task-generation logic.
- **Consistent lifecycle** — PlannedTask status (PENDING → DONE/CANCELLED) naturally reflects whether the reminder was acted upon, without needing a separate dismissed/acknowledged state.

**Negative:**

- **No standalone reminders** — a reminder cannot exist without an associated task. If a future requirement calls for "remind me to check the flock" without a concrete due action, it would not fit this model.
- **Notification triggering still unaddressed** — while the data model supports reminders, the mechanism to trigger push notifications or alerts (e.g., at `reminderDate`) is not implemented. This ADR covers only the data model and calendar display decision; notification delivery is deferred.
- **Reminder flexibility limited** — recurring reminders (e.g., "remind me every 3 months") are not natively supported. A recurring care protocol would need to generate multiple PlannedTask records.
- **Single-individual scope** — PlannedTask is scoped to one `individualId`. Flock-wide reminders ("trim all hooves in April") must be modeled as N individual tasks or a future batch abstraction.
