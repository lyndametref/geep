# REQ-01.003: For individual entries without an assigned BDTA number yet, the system allows later BDTA assignment.

As a sheperd, I want to create individual entries even when the BDTA number is not yet available so that I can capture important information immediately and complete identification later.

Group: REQ-01 Individual Management
Criticality Must have

## Description
Some individual entries, such as newborn or stillborn entries, may be created before the BDTA number is known. The system must allow the BDTA number to be assigned later without requiring the entry to be recreated.

## Acceptance Criteria
- An individual entry can be created without a BDTA number.
- A BDTA number can be assigned later to an existing individual entry.
