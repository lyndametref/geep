# BR-003: Lifecycle Date Constraints

## Description

birthDate is mandatory, deathDate is optional and captured when known.

## Rationale

Based on requirement [REQ-01.004](../requirements/business/REQ-01.004.md) — Lifecycle dates (birth and death tracking), this rule mandates that birthDate is always recorded for every individual (mandatory field). deathDate remains optional and is captured when a death event occurs.

## Applicability

Applies to individual lifecycle management. birthDate is set at creation and is mandatory (non-null). deathDate is set at death event recording and is nullable.
