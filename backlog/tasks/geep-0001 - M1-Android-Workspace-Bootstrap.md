---
id: GEEP-0001
title: M1 Android Workspace Bootstrap
status: Refined
assignee:
  - '@android-dev'
created_date: ''
updated_date: '2026-05-26 22:09'
labels:
  - android
  - build-system
milestone: MILESTONE-1
dependencies: []
references:
  - docs/guidelines/code/CODE-012-monorepo-structure.md
  - docs/requirements/non-functional/NFR-07.001.md
  - backlog/tasks/geep-0002 - M1-Mobile-App-Module-Architecture.md
priority: high
ordinal: 1017
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->

Bootstrap the Android workspace skeleton under apps/appAndroid/ with Gradle build system, dependency injection framework (Hilt), navigation framework (Jetpack Navigation Compose), and an initial :app module. This is the foundational task that all other M1 tasks depend on.

The :app module is the initial module; additional modules are added as their architecture is defined in GEEP-0002 (M1 Mobile App Module Architecture). The Android project lives in apps/appAndroid/ per CODE-012 (Monorepo Structure) as this repository hosts multiple applications (mobile, web, backend).

<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria

<!-- AC:BEGIN -->

- [ ] #1 Gradle project is configured with Kotlin, Compose, Room, Hilt, and Navigation dependencies

- [ ] #2 ProGuard/R8 configuration is included with baseline rules

- [ ] #3 Multi-module project structure is established with a placeholder for each module defined in the module architecture (GEEP-0002), starting with :app as the initial module

- [ ] #4 Hilt dependency injection is configured in the :app module with a common DI module structure ready for feature modules

- [ ] #5 Jetpack Navigation Compose is set up in the :app module with navigation infrastructure (NavHost, nav controller) ready for feature module routes

- [ ] #6 Application builds without errors and runs on an Android emulator at both the minimum supported version (API 35, Android 15) and the latest stable API level (per NFR-07.001)
  
  <!-- AC:END -->

## Implementation Plan

<!-- SECTION:PLAN:BEGIN -->

1. Create the Android project root structure at apps/appAndroid/ with Gradle wrapper, settings.gradle.kts, and root build.gradle.kts

2. Create the :app module under apps/appAndroid/ with application class, Hilt entry point, and basic Compose setup

3. Configure Hilt dependency injection in :app with a DI module structure

4. Set up Jetpack Navigation Compose with NavHost and nav controller in the :app module

5. Add ProGuard/R8 baseline configuration

6. Verify the project builds successfully with ./gradlew assembleDebug from apps/appAndroid/
   
   <!-- SECTION:PLAN:END -->

## Definition of Done

<!-- DOD:BEGIN -->

- [ ] #1 Project builds successfully with ./gradlew assembleDebug from apps/appAndroid/

- [ ] #2 All module placeholders are compilable

- [ ] #3 Documentation updated
  
  <!-- DOD:END -->
