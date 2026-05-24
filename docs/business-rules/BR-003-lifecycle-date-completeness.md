# BR-003: Lifecycle Date Completeness

## Description

birthDate is mandatory. deathDate is optional and captured when known. For stillborn individuals, birthDate and deathDate are both present and identical.

## Rationale

Based on requirement REQ-01.004 — Lifecycle dates (birth and death tracking), this rule mandates that birthDate is always recorded for every individual (mandatory field). deathDate remains optional and is captured when a death event occurs. For stillborn individuals, birthDate and deathDate are set to the same value to reflect that the individual was born dead while maintaining data consistency.

## Applicability

Applies to individual lifecycle management. birthDate is set at creation and is mandatory (non-null). deathDate is set at death event recording and is nullable. Stillborn status forces both dates to the same value.
