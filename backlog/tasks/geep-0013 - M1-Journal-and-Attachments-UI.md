---
id: GEEP-0013
title: M1 Journal & Attachments UI
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, ui, feature-journal]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005, GEEP-0008]
ordinal: 13
---

## Description

Implement the chronological journal view in the `:feature:journal` module. Displays a per-individual journal of all records (observations, interventions, future events) in chronological order with attachment support (photos, PDFs). Supports REQ-04.006.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Journal screen for an individual displays all records (observations, interventions, future events) in reverse chronological order — covers REQ-04.006
- [ ] #2 Journal items show type icon, date, title/summary, and attachment indicator
- [ ] #3 User can add photo attachments to any journal entry (from camera or gallery) — covers REQ-04.006 photo support
- [ ] #4 User can add PDF document attachments to any journal entry (from file picker) — covers REQ-04.006 PDF support
- [ ] #5 Tapping a journal entry shows full detail view with all attachments displayed inline
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Feature module compiles
- [ ] #2 Journal displays records chronologically from test data
- [ ] #3 Attachment add/view flow works for photos and PDFs
<!-- DOD:END -->
