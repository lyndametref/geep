# REQ-15.002 Custom intervention type creation

## User Story
As a flock manager, I want to create new intervention types so that I can record interventions specific to my farm.

## Group
REQ-15 User-Defined Event and Task Types

## Criticality
Could have

## Description
The system must allow the flock manager to define custom intervention types beyond the default set provided by product evolution (see REQ-13.001). Custom intervention types function identically to built-in types in intervention entry forms and care management workflows.

## Acceptance Criteria
- Flock manager can define a new intervention type with a name and description.
- New custom intervention type appears in intervention entry forms alongside built-in types.
- Custom intervention types can be edited after creation.
- Custom intervention types can be deactivated (soft-deleted) without affecting existing intervention records. Deactivated types cannot be selected when creating new interventions.
- Custom intervention type name must be unique across all intervention types (built-in and custom, case-insensitive).
