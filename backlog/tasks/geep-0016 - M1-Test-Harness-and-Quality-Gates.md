---
id: GEEP-0016
title: M1 Test Harness & Quality Gates
status: To Do
assignee: [@quality-gatekeeper]
priority: HIGH
milestone: MILESTONE-1
labels: [testing, quality]
dependencies: [GEEP-0001]
ordinal: 16
---

## Description

Set up test infrastructure and quality gates. Includes unit test framework, integration test framework, linting configuration, and CI pipeline for the mobile app. This task is foundational for ensuring all other tasks produce quality code.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Unit test framework is configured with JUnit 5 and MockK for all modules (core and feature)
- [ ] #2 Integration test framework is configured for Room database tests using in-memory database instances
- [ ] #3 Compose UI test framework is configured for screenshot and interaction testing
- [ ] #4 Detekt (or ktlint) linting is configured for the entire project with baseline rules
- [ ] #5 CI pipeline script (GitHub Actions or similar) is configured to run lint + unit tests + integration tests on each PR
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 All test frameworks compile and sample test passes
- [ ] #2 Linting runs without errors on current codebase
- [ ] #3 CI pipeline executes successfully
<!-- DOD:END -->
