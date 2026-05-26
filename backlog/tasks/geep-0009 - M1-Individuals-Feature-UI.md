---
id: GEEP-0009
title: M1 Individuals Feature UI
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, ui, feature-individuals]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005, GEEP-0008]
ordinal: 9
---

## Description

Implement the Individuals feature UI in the `:feature:individuals` module. This covers the individual list screen, detail view, and create/edit forms with field validation. Supports REQ-01 (all sub-reqs 01.001-01.008) and REQ-11.001 (mobile create/update for individuals).

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Individual list screen displays all active flock individuals with name, official ID, sex icon, and flock status — supports pull-to-refresh and search by name/officialId — covers REQ-01
- [ ] #2 Individual detail view shows all fields (name, officialId, birthDate, deathDate, sex, colorPattern, parents, flock membership timeline, notes) with edit capability — covers REQ-01.001-01.008
- [ ] #3 Create individual form includes fields for name (optional), birthDate (mandatory), sex, parents (optional via lookup), stillborn flag, flock membership entry — with field-level validation — covers REQ-11.001 (individuals part)
- [ ] #4 Edit individual form allows updating all mutable fields including deferred assignment of officialId (REQ-01.003), deathDate, flock exit with reason/date (REQ-01.008)
- [ ] #5 Individual list filters by flock status (active/lineage), with lineage individuals excluded from day-to-day views per REQ-01.008
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Feature module compiles
- [ ] #2 UI component previews render correctly
- [ ] #3 Create/edit flows tested end-to-end
<!-- DOD:END -->
