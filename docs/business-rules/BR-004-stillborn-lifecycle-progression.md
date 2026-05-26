# BR-004: Stillborn Lifecycle Progression

## Description

Stillborn individuals have identical birthDate and deathDate (both set to the same value), support identity and lineage capture but cannot be associated any Record or Future Events.

## Rationale

Based on requirement [REQ-01.004](../requirements/business/REQ-01.004.md) — Lifecycle dates (birth and death tracking), this rule distinguishes stillborns from live individuals. For stillborns, birthDate and deathDate are set to the same value to reflect that the individual was born dead while maintaining data consistency. Stillborns are recorded for genealogical completeness (parentage, lineage) and inventory accuracy, but cannot be assigned any other records or future events. 

## Applicability

Applies from individual creation when the individual is maked as stillborn. Once marked stillborn, the individual is excluded from all flock management workflows (observations, interventions, batch operations, dashboards) but remains visible in genealogy views.
