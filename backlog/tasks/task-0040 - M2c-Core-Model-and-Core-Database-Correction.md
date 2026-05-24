---
id: TASK-0040
title: M2c Core-Model and Core-Database Correction
status: To Do
assignee:
  - '@android-dev'
created_date: '2026-05-22 21:14'
updated_date: '2026-05-22 21:15'
labels:
  - mobile
  - domain-model
  - database
milestone: Iteration 1 Mobile-Only MVP
dependencies:
  - TASK-0039
priority: high
ordinal: 2500
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
After TASK-0039 finalizes business rules and state definitions, the core-model Kotlin entities and core-database Room schema may need corrections to align with the finalized business rules, state machines, and enum definitions. This task covers:

- Update core-model entity classes to reflect finalized business rules
- Align enum/state definitions with approved state machines
- Update core-database Room entities, DAOs, and type converters if needed
- Ensure core-model and core-database are consistent with each other
- Update unit tests to match corrected entities
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 Core-model entities match finalized business rules (BR-001 through BR-019)
- [ ] #2 Enum and state definitions match approved state machines from TASK-0039
- [ ] #3 Core-database entities, DAOs, and type converters are aligned with corrected core-model
- [ ] #4 All existing tests pass after corrections
- [ ] #5 No regressions in downstream consumer modules (feature-*, core-rules)
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
