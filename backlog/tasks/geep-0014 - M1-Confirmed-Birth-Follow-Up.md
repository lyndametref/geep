---
id: GEEP-0014
title: M1 Confirmed Birth Follow-Up
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, feature, lambing]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005, GEEP-0006, GEEP-0010]
ordinal: 14
---

## Description

Implement the post-lambing workflow in the `:feature:observations` module. When a lambing observation is confirmed, the system proposes creation of lamb Individual records and a weaning PlannedTask for each lamb. Supports REQ-04.005 and BR-022.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 When a lambing observation is recorded for a ewe (female individual), the system presents a dialog proposing to create N lamb individuals where N is the lamb count from the observation — covers REQ-04.005
- [ ] #2 For each created lamb individual, a weaning PlannedTask is automatically generated with a default weaning interval (configurable, default 90 days after birth) — covers REQ-04.005 and BR-022
- [ ] #3 User can accept, defer (create later), or dismiss the lamb creation proposal
- [ ] #4 User can modify the proposed weaning date when accepting the proposal
- [ ] #5 Only female individuals (ewes) can have lambing observations recorded — BR-022 sex constraint enforced in UI
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Lambing flow works end-to-end: observation → proposal → lamb creation → weaning task
- [ ] #2 All BR-022 rules are enforced
- [ ] #3 UI handles accept/defer/dismiss all states
<!-- DOD:END -->
