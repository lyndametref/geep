# BR-004: Stillborn Lifecycle Progression

## Description

Stillborn individuals support identity and lineage capture but do not progress through the post-birth observation lifecycle.

## Rationale

Based on requirement REQ-01.004 — Lifecycle dates (birth and death tracking), this rule distinguishes stillborns from live individuals. Stillborns are recorded for genealogical completeness (parentage, lineage) and inventory accuracy, but do not generate weight observations, health checks, interventions, or any other post-birth journal entries. Their living status is permanently set to deceased.

## Applicability

Applies at individual creation when stillborn flag is set. Once marked stillborn, the individual is excluded from all flock management workflows (observations, interventions, batch operations, dashboards) but remains visible in genealogy views.
