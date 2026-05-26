---
id: GEEP-0003
title: M1 Core Model Implementation
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, domain-model]
dependencies: [GEEP-0001, GEEP-0002]
ordinal: 3
---

## Description

Implement pure domain entity classes in the `:core:model` module. These are Kotlin data classes with NO database annotations, NO Android framework dependencies. Covers Individual, Record, Observation, Intervention, Attachment, FutureEvent, PredictedEvent, PlannedTask, WaitingDelay and their value objects, enums, and state machines.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Individual entity is implemented with fields: id (Long), name (String?), officialId (String?), birthDate (LocalDate), deathDate (LocalDate?), sex (Sex enum), colorPattern (String?), stillborn (Boolean), belongsToFlock (Boolean), sireId (Long?), damId (Long?), notes (String?)
- [ ] #2 Observation, Intervention, Record, Attachment, FutureEvent, PredictedEvent, PlannedTask, WaitingDelay entities are implemented as Kotlin data classes with all attributes from the domain model
- [ ] #3 Sex enum (MALE, FEMALE), RecordType enum (OBSERVATION, INTERVENTION, FUTURE_EVENT), PredictionStatus, TaskStatus, DelayStatus enums are defined
- [ ] #4 FlockEntryReason (BIRTH, PURCHASE) and FlockExitReason (SOLD, SLAUGHTERED, DECEASED) enums are defined
- [ ] #5 No database, Android, or platform-specific annotations/imports exist in the core model module — it is a pure Kotlin library module
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 All domain entities compile against the :core:model module
- [ ] #2 Attribute lists are consistent with `docs/domain-model/` and `docs/specs/mobile-database-schema.md`
- [ ] #3 Tests for entity construction and value object constraints pass
<!-- DOD:END -->
