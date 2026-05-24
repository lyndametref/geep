# BR-019: Flock Membership Tracking

## Description

An individual's flock membership is derived from FLOCK_ENTRY and FLOCK_EXIT observation types. An individual with belongsToFlock = false and no membership observations is a Lineage individual, excluded from flock management views but visible in genealogy.

## Rationale

Based on requirement REQ-01.008 — Flock membership management, this rule defines how active flock membership is tracked and how Lineage individuals (ancestors not in the current flock) are distinguished. Membership is derived from observations rather than stored as a mutable flag, preserving an auditable history of entries and exits. Lineage individuals are excluded from day-to-day operations but remain in the genealogy graph for parentage and inheritance analysis.

## Applicability

Applies to individual views and queries. FLOCK_ENTRY records the reason (BIRTH or PURCHASE). FLOCK_EXIT records the reason (SOLD, SLAUGHTERED, or DECEASED). Current membership is derived as the latest observation type for the individual.
