---
id: TASK-0034
title: 'M4-LSB: Enable ProGuard/R8 obfuscation for release builds'
status: To Do
assignee: []
created_date: '2026-05-17 19:26'
labels:
  - mobile
  - build
dependencies:
  - TASK-0005
references:
  - docs/MOBILE_SECURITY_POLICY.md
modified_files:
  - appAndroid/app/build.gradle.kts
  - appAndroid/app/proguard-rules.pro
priority: high
ordinal: 27000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Enable minification, obfuscation, and optimisation in the release build type. Write and maintain ProGuard keep rules for reflection, serialisation, and library targets (Room, SQLCipher, kotlinx).
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 isMinifyEnabled set to true in release build type
- [ ] #2 ProGuard keep rules cover Room entities, DAOs, and SQLCipher classes
- [ ] #3 Release APK builds and runs without ClassNotFoundException or MissingMethodException
- [ ] #4 Obfuscation verified via decompilation of release APK
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
