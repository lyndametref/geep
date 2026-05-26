---
id: GEEP-0001
title: M1 Android Workspace Bootstrap
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, build-system]
ordinal: 1
---

## Description

Set up the Android project skeleton with Gradle build system, dependency injection framework (Hilt), navigation framework (Jetpack Navigation Compose), and a multi-module project structure. This is the foundational task that all other M1 tasks depend on.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Gradle project is configured with Kotlin, Compose, Room, Hilt, and Navigation dependencies
- [ ] #2 Multi-module project structure is established with `:app`, `:core:model`, `:core:database`, `:core:rules`, `:core:security`, `:feature:individuals`, `:feature:observations`, `:feature:genealogy`, `:feature:calendar`, `:feature:journal`, `:feature:backup` modules
- [ ] #3 Hilt dependency injection is configured across all modules with a common DI module
- [ ] #4 Jetpack Navigation Compose is set up with navigation graph placeholders for all feature modules
- [ ] #5 ProGuard/R8 configuration is included with baseline rules
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Project builds successfully with `./gradlew assembleDebug`
- [ ] #2 All module placeholders are compilable
- [ ] #3 Documentation updated
<!-- DOD:END -->
