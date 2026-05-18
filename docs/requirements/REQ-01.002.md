# REQ-01.002: Each individual entry can have an official number associated.

As a shepherd, I want to be able to provide an official number for my animals, either at creation or later, so that I can comply with regulations while keeping flexibility on when I make the assignment.

Group: REQ-01 Individual Management
Criticality Must have

## Description
The official number (for example AMD/TVD/BDTA in Switzerland) can be provided by the sheep owner at any point in the individual's management lifecycle. Sometimes the official number is not available when the owner creates the entry, so assigning it later must be possible.

## Acceptance Criteria
- For registered animals, each individual entry can have an official number associated.
- The official number can be empty.
- The official number is unique.
- The official number cannot be used to reference the animal across the system.
