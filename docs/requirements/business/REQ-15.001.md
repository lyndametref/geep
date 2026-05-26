# REQ-15.001 Custom observation type creation

## User Story
As a flock manager, I want to create new observation types so that I can record observations specific to my farm that are not covered by default types.

## Group
[REQ-15 User-Defined Event and Task Types](REQ-15.md)

## Criticality
Could have

## Description
The system must allow the flock manager to define custom observation types beyond the default set provided by product evolution (see [REQ-04.001](REQ-04.001.md)). Custom observation types function identically to built-in types in observation entry forms and reports.

## Acceptance Criteria
- Flock manager can define a new observation type with a name and description.
- New custom observation type appears in observation entry forms alongside built-in types.
- Custom observation types can be edited after creation to rename, delete or add optional attributes.
- Custom observation types can be deactivated (soft-deleted) without affecting existing observations. They cannot be choosen for new observation creation.
- Custom observation type name must be unique across all observation types (built-in and custom, case-insensitive).