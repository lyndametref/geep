# REQ-04.005 A confirmed lambing proposes creation of lamb records as well as a weaning event after a configurable interval.

## User Story
As a shepherd, I want confirmed lambings to generate follow-up weaning actions so that newborn and weaning planning are not missed.

## Group
[REQ-04 Observations and Reproductive Planning](REQ-04.md)

## Criticality
Must have

## Description
A confirmed lambing must trigger a proposal to create lamb entries and a planned weaning event after a configurable interval for all the lambs.

## Acceptance Criteria
- When a lambing is confirmed, the system proposes creating lamb entries.
- When a lambing is confirmed, the system generates a planned weaning event after a configurable interval after the confirmed lambing date for each created lamb.
- Multiple lambs can be associated to a single lambing event for the dam
- only ewe (female individual) can have lambing events recorded.
