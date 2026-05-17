---
id: TASK-0038
title: 'M4-LSB: Replace Random() with SecureRandom for all IDs'
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
priority: low
ordinal: 31000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Audit the codebase for any usage of java.util.Random or kotlin.random.Random used for identifier generation (UUIDs, attachment filenames, entity IDs) and replace with java.security.SecureRandom.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 All generated IDs use SecureRandom
- [ ] #2 No usages of java.util.Random or kotlin.random.Random for ID generation remain
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
