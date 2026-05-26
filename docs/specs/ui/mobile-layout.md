# Mobile layout

## Scope

Mobile-specific layout and navigation. All UI workflows follow the specs defined in the sibling view files (`calendar-view.md`, `journal-view.md`, etc.) — this document only covers mobile adaptations.

## Landing page

The first screen shown when the application starts. It serves as a dashboard with a quick overview of the flock.

- **Flock summary** at the top: total individuals, split by sex (M / F), count of active and exited
- **Upcoming events** section: next 5 calendar events (tasks due, predicted events, reminders) ordered by date (see `calendar-view.md`)
- **Quick actions** bar: "Add individual", "Record observation", "Record intervention" buttons
- **Sync status** indicator in the header

Tapping any event or entry navigates to its detail. The landing page is skippable — tapping the Flock tab goes directly to the flock list.

## Navigation

Bottom tab bar with 3 tabs:

| Tab | Content |
|-----|---------|
| **Flock** | Flock view |
| **Calendar** | Calendar view |
| **More** | Settings, data export, help |

## Flock

Defined in `flock-view.md`

## Individual detail view

Accessed when clicking on an individual in the flock view or in calendar view.

Defined in `individual-view.md`

## Calendar view

Defined in `calendar-view.md`

## Visual adaptation

- Single column layout throughout
- Bottom sheet for forms and pickers instead of modals
- Thumbnails for attachments, full-screen viewer on tap
- Haptic feedback on key actions (record saved, task completed)
