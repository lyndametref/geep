# REQ-15.004 Custom predicted event type creation

## User Story
As a flock manager, I want to define custom predicted event types so that the system can anticipate events specific to my operations.

## Group
[REQ-15 User-Defined Event and Task Types](REQ-15.md)

## Criticality
Could have

## Description
The system must allow the flock manager to define custom predicted event types with configurable timing rules. These predicted events appear in the calendar view alongside system-generated predictions (e.g., lambing from mating as defined in [REQ-04.004](REQ-04.004.md)).

## Acceptance Criteria
- Flock manager can define a new predicted event type with a name, description, and expected timing rules (e.g., X to Y days after a trigger event).
- Predicted events of the custom type appear in the calendar view.
- Custom predicted event types can be edited after creation.
- Custom predicted event types can be deactivated (soft-deleted) without affecting existing predictions. Deactivated types cannot be selected when creating new predicted events.
- Custom predicted event type name must be unique across all predicted event types (built-in and custom, case-insensitive).
- Trigger events for custom predicted event types can be any observation or intervention type (built-in or custom).
