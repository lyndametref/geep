---
id: GEEP-0010
title: M1 Observations & Batch Entry UI
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, ui, feature-observations]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005, GEEP-0008]
ordinal: 10
---

## Description

Implement the Observations feature UI in the `:feature:observations` module. Covers observation entry forms for weight, health, and reproduction events, batch entry with filters, and observation type management. Supports REQ-04.001-04.003 and REQ-11.001 (observations part).

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Observation entry form supports creating observations of types: weight evolution (with numeric value+unit), health observation (free text), mating observation — covers REQ-04.001
- [ ] #2 Batch entry flow: user selects individuals via filters (age, sex, text search) or manual checkboxes, then applies the same observation to all selected — covers REQ-04.002 and REQ-04.003
- [ ] #3 Batch selection UI implements all filters per `docs/specs/batch-selection-filters.md`: text search, sex filter, age filter (by years and by birth year), and manual checkbox selection
- [ ] #4 Observation list for an individual shows all observations chronologically with type, date, and content summary
- [ ] #5 Flock entry/exit observations can be created through the observation flow (FLOCK_ENTRY with reason BIRTH/PURCHASE, FLOCK_EXIT with reason SOLD/SLAUGHTERED/DECEASED) — covers REQ-01.008 observation-based tracking
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Feature module compiles
- [ ] #2 Batch selection filters are tested with all filter combinations
- [ ] #3 Observation creation for single and batch flows works end-to-end
<!-- DOD:END -->
