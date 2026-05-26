# Calendar view

## Scope

A flock-wide calendar showing records (observations and interventions), predicted future events, reminder (planned tasks) and quarantine periods, and reminders for all individuals.

## Layout

Standard month grid. Each day cell lists the events for that day. Tapping a day open a day view will all entry on this date. Tapping an event on the day view navigates to the relevant detail.

## Event types and placement

| Type | Source | Date field(s) | Placement |
|------|--------|---------------|-----------|
| Past observation | Observation | `observedAt` | Single day |
| Past intervention | Intervention | `performedAt` | Single day |
| Predicted event | PredictedEvent | `earliestDate` – `latestDate` | Range spanning from earliest to latest date |
| Task due | PlannedTask | `dueDate` | Single day |
| Quarantine | WaitingDelay | up to `delayElapsedAt` | Range from source record date (`performedAt` for Intervention, `observedAt` for Observation) to `delayElapsedAt` |

## Predicted event range display

A predicted event with a date range is rendered as a banner spanning the cells from `earliestDate` to `latestDate`. The event label appears on each day of the range.

## Filtering

**By event type:** A filter bar above the calendar allows toggling event type categories on or off. Categories: **Past observations**, **Past interventions**, **Predicted events**, **Reminders**, **Quarantine periods**. When a category is hidden, all events of that type are excluded from the view. Multiple categories can be toggled simultaneously.

**By individual:** A searchable individual selector restricts the view to events for a single individual. When no individual is selected, the calendar shows flock-wide events.

## Visual distinction

- **Past events**: normal text weight
- **Predicted events**: italic or dashed border
- **Reminders**: distinct color
- **Quarantine periods**: distinct color, shown as a shaded range bar

## Per-individual drill-down

Tapping a calendar entry navigates to the individual's journal scrolled to the relevant entry.
