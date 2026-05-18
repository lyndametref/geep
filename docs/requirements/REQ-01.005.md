# REQ-01.005: Each individual entry includes information about the individual current state and history.

As a shepherd, I want to manage complete information for each sheep so that I can track identity, lifecycle, and lineage.

Group: REQ-01 Individual Management
Criticality Must have

## Description
Each individual entry includes at least:
- sex
- alive/dead status
- dam (female parent)
and if available:
- sire (male parent)

complete phenotyping is separated and will be included in REQ-03


## Acceptance Criteria
- An individual entry includes a sex field.
- An individual entry includes parent references.
- An individual entry includes an alive/dead status field.
