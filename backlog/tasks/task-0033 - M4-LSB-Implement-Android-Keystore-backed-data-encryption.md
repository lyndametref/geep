---
id: TASK-0033
title: 'M4-LSB: Implement Android Keystore-backed data encryption'
status: To Do
assignee: []
created_date: '2026-05-17 19:26'
updated_date: '2026-05-17 19:29'
labels:
  - mobile
  - security
dependencies:
  - TASK-0005
references:
  - docs/MOBILE_SECURITY_POLICY.md
modified_files:
  - appAndroid/app/build.gradle.kts
  - appAndroid/core-database/build.gradle.kts
priority: high
ordinal: 32000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Add SQLCipher for Room DB encryption, EncryptedSharedPreferences, and EncryptedFile for attachment storage — all transparently backed by the Android Keystore MasterKey with zero UX friction.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 MasterKey singleton created with AES256_GCM scheme and setUnlockedDeviceRequired(true)
- [ ] #2 Room database migrated to SQLCipher via SupportFactory
- [ ] #3 All SharedPreferences replaced with EncryptedSharedPreferences
- [ ] #4 Photo/file attachments stored via EncryptedFile
- [ ] #5 App functions normally without any user-facing passphrase or PIN prompt
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Tests pass
- [ ] #2 Documentation updated
- [ ] #3 No regressions introduced
<!-- DOD:END -->
