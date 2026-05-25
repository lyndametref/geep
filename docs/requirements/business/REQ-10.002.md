# REQ-10.002 Pasture management supports parasite-oriented rotation operations.

## User Story
As a shepherd, I want to manage pasture usage so that parasite exposure can be reduced.

## Group
REQ-10 Pasture Management and Mapping

## Criticality
Should have

## Description
The system must support pasture usage tracking used in parasite-oriented rotation management, including movement recording, occupancy tracking, recovery quarantine calculation, and end-of-quarantine event creation.

## Acceptance Criteria
- A pasture can be created.
- Movements to and from a pasture can be recorded for a batch of animals.
- The system tracks how long a pasture is occupied.
- When no individuals remain on a pasture, a configurable recovery quarantine period is calculated.
- The system creates a future event for the end of the recovery quarantine period.
