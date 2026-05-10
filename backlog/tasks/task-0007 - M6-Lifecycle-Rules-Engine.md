---
id: TASK-0007
title: M6 Lifecycle Rules Engine
status: To Do
assignee: []
created_date: '2026-05-08 14:35'
updated_date: '2026-05-08 14:36'
labels:
  - mobile
  - rules
milestone: Iteration 1 Mobile-Only MVP
dependencies:
  - TASK-0006
ordinal: 7000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Implement deterministic lifecycle reminder derivation rules.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 Mating creates birth reminder at +140 days.
- [ ] #2 Birth creates weaning reminder at +90 days.
- [ ] #3 Treatment creates quarantine end reminder.
- [ ] #4 Rule execution is idempotent and avoids duplicate reminders.
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
