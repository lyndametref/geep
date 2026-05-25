# REQ-15.005 Custom waiting delay type creation

## User Story
As a flock manager, I want to define waiting delay types so that I can track mandatory waiting periods (e.g., meat withdrawal, milk withdrawal) after specific interventions.

## Group
REQ-15 User-Defined Event and Task Types

## Criticality
Could have

## Description
The system must allow the flock manager to define custom waiting delay types that specify a duration associated with a given intervention type (see REQ-13.004 for quarantine periods). Waiting delays appear on the individual's journal and calendar view.

## Acceptance Criteria
- Flock manager can define a new waiting delay type with a name, associated intervention type (built-in or custom), and duration.
- Waiting delays appear on the individual's chronological journal (REQ-04.006, REQ-13.005).
- Custom waiting delay types can be edited after creation.
- Custom waiting delay types can be deactivated (soft-deleted) without affecting existing delay records. Deactivated types cannot be selected when creating new waiting delays.
- Custom waiting delay type name must be unique across all waiting delay types (built-in and custom, case-insensitive).
