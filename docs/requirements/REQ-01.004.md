# REQ-01.004: Each individual entry includes a birth date, with an optional death date; for stillborn entries, both dates are the same.

As a shepherd, I want to record birth and, when dead, death dates for each sheep so that lifecycle information remains complete and accurate.

Group: REQ-01 Individual Management
Criticality Must have

## Description
Each individual entry must support storing a birth date and, when applicable, a death date. For stillborn entries, the birth date and death date must be the same.

## Acceptance Criteria
- An individual entry supports a birth date (mandatory) and a death date (optional).
- For stillborn entries, the birth date and death date are identical.
