# REQ-01.002: Each individual entry can have an official identifier associated.

As a shepherd, I want to be able to provide an official identifier for my animals, either at creation or later, so that I can comply with regulations while keeping flexibility on when I make the assignment.

Group: REQ-01 Individual Management
Criticality Must have

## Description
The official identifier (for example AMD/TVD/BDTA in Switzerland) can be provided by the sheep owner at any point in the individual's management lifecycle. Sometimes the official identifier is not available when the owner creates the entry, so assigning it later must be possible.

## Acceptance Criteria
- For registered animals, each individual entry can have an official identifier associated.
- The official identifier can be empty.
- The official identifier is unique.
- The official identifier cannot be used to reference the animal across the system.
