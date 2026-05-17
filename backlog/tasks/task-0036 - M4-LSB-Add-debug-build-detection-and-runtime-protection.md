---
id: TASK-0036
title: 'M4-LSB: Add debug-build detection and runtime protection'
status: To Do
assignee: []
created_date: '2026-05-17 19:26'
updated_date: '2026-05-17 19:56'
labels:
  - mobile
  - security
milestone: Iteration 1 Mobile-Only MVP
dependencies:
  - TASK-0005
references:
  - docs/MOBILE_SECURITY_POLICY.md
priority: medium
ordinal: 5030
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Implement a runtime check that detects when the app is running in debug mode (e.g. debuggable flag, adb-connected debugger) on a release build and terminates with a clear message.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 App checks BuildConfig.DEBUG and ApplicationInfo.flags at startup in release builds
- [ ] #2 App terminates with user-facing dialogue if debug mode detected on release build
- [ ] #3 Check passes silently on legitimate release builds
- [ ] #4 Unit test verifies detection logic
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
