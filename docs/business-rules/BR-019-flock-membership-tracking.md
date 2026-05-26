# BR-019: Flock Membership Tracking

## Description

An Individual's flock membership is derived from entrance and exit from the flock (observations). Individuals lacking either are classified as Lineage Individuals excluded from flock management but visible in genealogy.

## Rationale

Based on requirement [REQ-01.008](../requirements/business/REQ-01.008.md) — Flock membership management, this rule defines how active flock membership is tracked and how Lineage Individuals (ancestors not in the current flock) are distinguished. Membership is derived from Observations, preserving an auditable history of entries and exits. Lineage Individuals are excluded from day-to-day operations but remain in the genealogy graph for parentage and inheritance analysis.

## Applicability

Applies to Individual views and queries.
