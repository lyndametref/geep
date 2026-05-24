# BR-006: Batch Entry Domain Representation

## Description

Batch entry is a UI workflow concern; the domain model persists only resulting observations linked to the affected individuals.

## Rationale

Based on requirements REQ-04.002 — Batch observation entry, and REQ-13.002 — Batch intervention entry, this rule keeps the domain model clean by treating batching as a presentation-layer concern. When a user records the same observation or intervention for multiple individuals in one UI action, the system creates one record per individual. No "batch entity" exists in the domain; batch is purely a UX convenience.

## Applicability

Applies to observation and intervention creation workflows. The UI may offer batch selection, but the persistence layer always stores individual records per individual.
