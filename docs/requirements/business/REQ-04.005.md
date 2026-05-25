# REQ-04.005 A confirmed birth proposes creation of lamb records as well as a weaning event after a configurable interval.

## User Story
As a shepherd, I want confirmed births to generate follow-up weaning actions so that newborn and weaning planning are not missed.

## Group
REQ-04 Observations and Reproductive Planning

## Criticality
Must have

## Description
A confirmed birth must trigger a proposal to create lamb entries and a planned weaning event after a configurable interval for all the lambs.

## Acceptance Criteria
- When a birth is confirmed, the system proposes creating lamb entries.
- When a birth is confirmed, the system generates a planned weaning event after a configurable interval after the confirmed birth date for each created lamb.
- Multiple lambs can be associated to a single birth event for the dam
- only ewe (female individual) can have birth events recorded.
