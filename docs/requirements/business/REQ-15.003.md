# REQ-15.003 Custom planned task type creation

## User Story
As a flock manager, I want to create custom planned task types so that I can schedule farm-specific tasks.

## Group
REQ-15 User-Defined Event and Task Types

## Criticality
Could have

## Description
The system must allow the flock manager to define custom planned task types for scheduling recurring or one-off tasks that are specific to their farming operations. These tasks appear on the calendar view alongside system-generated events.

## Acceptance Criteria
- Flock manager can define a new planned task type with a name, description, and optional recurrence rule.
- Planned tasks of the custom type appear on the calendar view (REQ-05).
- Custom planned task types can be edited after creation.
- Custom planned task types can be deactivated (soft-deleted) without affecting existing task records. Deactivated types cannot be selected when creating new planned tasks.
- Custom planned task type name must be unique across all planned task types (built-in and custom, case-insensitive).
