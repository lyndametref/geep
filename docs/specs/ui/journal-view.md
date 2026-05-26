# Journal view

## Scope

A chronological journal per individual showing records (observations and interventions), predicted events, planned tasks, and waiting delays with attached media. The journal is the primary detail view when drilling down from the calendar.

## Layout

Chronological list ordered by the operational date descending (newest first): `observedAt` for observations, `performedAt` for interventions, `earliestDate` – `latestDate` for predicted events, `dueDate` for planned tasks, `delayElapsedAt` for waiting delays. Each entry displays the record type icon, date, summary line, and attachment indicators. Tapping an entry opens the full record detail.

## Entry types

| Type | Source | Date field | Summary |
|------|--------|------------|---------|
| Observation | `Observation` | `observedAt` | Observation type + first line of content |
| Intervention | `Intervention` | `performedAt` | Intervention type + first line of content |
| Predicted event | `PredictedEvent` | `earliestDate` – `latestDate` | Predicted event type + date range |
| Planned task | `PlannedTask` | `dueDate` | Task title + status (Pending/Done/Cancelled) |
| Waiting delay | `WaitingDelay` | up to `delayElapsedAt` | Quarantine or withdrawal reason |

## Attachments

Each entry can have zero or more attachments (photos, PDFs). Attachments are displayed as thumbnails inline below the entry summary. Tapping a thumbnail opens a full-screen viewer for photos or a PDF viewer for documents.

## Filtering

**By entry type:** Toggle checkboxes for **Observations**, **Interventions**, **Predicted events**, **Planned tasks**, and **Waiting delays**. When a type is hidden, all entries of that type are excluded.

## Visual distinction

- **Observations**: normal weight, icon per observation type
- **Interventions**: normal weight, icon per intervention type
- **Predicted events**: italic or dashed border, shown with date range label (e.g., "Aug 14 – Aug 21")
- **Planned tasks**: distinct color, shown with status badge (Pending/Done/Cancelled)
- **Waiting delays**: distinct color, shown with a countdown label (e.g., "3 days remaining")

## Per-entry drill-down

Tapping an entry navigates to the full record detail view showing all fields, attachments, and related future events. For planned tasks, tapping navigates to the task detail view showing the task title, description, assignment, status, and due date.
