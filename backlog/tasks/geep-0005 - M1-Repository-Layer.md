---
id: GEEP-0005
title: M1 Repository Layer
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, data-access]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004]
ordinal: 5
---

## Description

Implement repository interfaces and implementations in the `:core:database` module that wrap DAOs and expose Flow-based reactive access for the UI layer. Repositories map between Room entities and domain model objects, providing a clean API for feature modules.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 IndividualRepository is implemented with methods: getIndividual(id), getAllIndividuals(), searchIndividuals(query, sex, ageRange), getActiveFlockIndividuals(), saveIndividual(), deleteIndividual() — all returning Flow where applicable
- [ ] #2 RecordRepository is implemented with methods: getRecordsForIndividual(individualId), getRecordsByType(type), saveRecord(record), getRecordsInDateRange(start, end)
- [ ] #3 ObservationRepository, InterventionRepository, FutureEventRepository are implemented wrapping the RecordRepository with type-specific operations
- [ ] #4 AttachmentRepository is implemented supporting save, getForRecord, delete operations with file URI handling
- [ ] #5 All repositories are designed as interfaces with Room-based implementations injectable via Hilt, and use Kotlin Flow for reactive observation
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Repository interfaces and implementations compile
- [ ] #2 Repository integration tests against in-memory Room database pass
- [ ] #3 Hilt bindings for all repositories are configured
<!-- DOD:END -->
