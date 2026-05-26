---
id: GEEP-0008
title: M1 Local Security Baseline
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, security]
dependencies: [GEEP-0001, GEEP-0002]
ordinal: 8
---

## Description

Implement the local security baseline in the `:core:security` module. Covers NFR-04.001 (Zero UX Friction Security) and NFR-04.003 (Data Encryption). Includes Android Keystore-backed data encryption, input validation, secure random IDs, ProGuard/R8 obfuscation, and debug-build detection.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Android Keystore-backed encryption is implemented for at-rest data, transparent to the user (no manual encryption actions required) — covers NFR-04.001 and NFR-04.003
- [ ] #2 Input validation utilities are implemented and integrated into all user-facing form screens (individual create/edit, observation entry)
- [ ] #3 All system-generated IDs use SecureRandom (or equivalent cryptographically secure random) instead of Random — covers NFR-04.001 secure ID generation
- [ ] #4 ProGuard/R8 obfuscation is enabled for release builds with proper rules to prevent stripping of Room, Hilt, and serialization classes
- [ ] #5 Debug-build detection and runtime protection are implemented (app behavior changes when running on debug builds vs release)
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Security module compiles
- [ ] #2 Encryption/decryption round-trip tests pass
- [ ] #3 Security audit confirms no hardcoded secrets or weak crypto
<!-- DOD:END -->
