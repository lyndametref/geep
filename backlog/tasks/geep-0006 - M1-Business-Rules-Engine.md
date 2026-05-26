---
id: GEEP-0006
title: M1 Business Rules Engine
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, business-rules]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005]
ordinal: 6
---

## Description

Implement the business rules engine in the `:core:rules` module. This covers lifecycle rules (BR-003 dates, BR-004 stillborn, BR-005 parentage), lambing prediction (BR-012), future event realization (BR-015), flock membership tracking (BR-019), and lambing process flow (BR-022). Rules are implemented as stateless services operating on domain models and repositories.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 LifecycleRulesService validates BR-003 (birthDate mandatory, deathDate optional) and BR-004 (stillborn has birthDate == deathDate, no records/future events allowed)
- [ ] #2 ParentageRulesService validates BR-005 (sire must be male, dam must be female, zero or more sires, zero or one dam)
- [ ] #3 LambingPredictionService implements BR-012: a mating observation derives a PredictedEvent with window of 140-150 days after observation
- [ ] #4 FutureEventRealizationService implements BR-015: confirming a PredictedEvent creates an Observation, confirming a PlannedTask creates an Intervention, referencing the source FutureEvent
- [ ] #5 FlockMembershipService implements BR-019 and BR-022: derives flock membership from FLOCK_ENTRY/FLOCK_EXIT observations, and the lambing process flow creates lamb individuals and weaning PlannedTasks
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 All rule services compile
- [ ] #2 Unit tests cover each business rule with happy path and edge cases
- [ ] #3 Hilt bindings for all rule services are configured
<!-- DOD:END -->
