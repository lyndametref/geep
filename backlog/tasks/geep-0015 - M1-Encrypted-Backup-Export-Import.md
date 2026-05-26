---
id: GEEP-0015
title: M1 Encrypted Backup Export/Import
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, feature, backup]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0004, GEEP-0005, GEEP-0008]
ordinal: 15
---

## Description

Implement encrypted backup export and import in the `:feature:backup` module. The backup serializes all database records to a JSON file, encrypts it with Android Keystore, and exports to the user's chosen location. Import reverses the process. Supports NFR-03.001 (data traceability) and NFR-04.003 (data encryption).

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Backup export serializes all database tables (individuals, records, observations, interventions, future_events, predicted_events, planned_tasks, waiting_delays, attachments metadata) to JSON
- [ ] #2 The exported backup file is encrypted using Android Keystore-backed encryption before writing to external storage — covers NFR-04.003
- [ ] #3 User can choose export location via SAF (Storage Access Framework) file picker
- [ ] #4 Backup import reads the encrypted file, decrypts it, deserializes JSON, and restores all data to the database
- [ ] #5 Import validates data integrity (checksum or hash) before restoring and shows a preview of what will be restored (counts per table)
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Feature module compiles
- [ ] #2 Export-then-import round-trip test succeeds with full data fidelity
- [ ] #3 Corrupted/forged backup files are rejected with clear error message
<!-- DOD:END -->
