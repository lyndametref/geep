---
id: TASK-0035
title: 'M4-LSB: Add input validation to all user-facing forms'
status: To Do
assignee: []
created_date: '2026-05-17 19:26'
labels:
  - mobile
  - security
dependencies:
  - TASK-0005
references:
  - docs/MOBILE_SECURITY_POLICY.md
modified_files:
  - appAndroid/feature-individuals/src
  - appAndroid/feature-genealogy/src
  - appAndroid/feature-journal/src
priority: medium
ordinal: 28000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Add input validation and sanitisation to every user-facing text field in the MVP. Validate length, character set, and expected format per field type. Reject or sanitise malformed input before persistence.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 BDTA identifier fields validate against expected format
- [ ] #2 Name and text fields enforce max length and reject control characters
- [ ] #3 Numeric fields (weight, BCS, dates) reject non-numeric input
- [ ] #4 Validation feedback shown inline on the form (Compose)
- [ ] #5 Injection characters are rejected or sanitised before DB write
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
