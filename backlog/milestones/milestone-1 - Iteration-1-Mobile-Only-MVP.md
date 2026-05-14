---
id: MILESTONE-1
title: Iteration 1 Mobile-Only MVP
status: Active
description: Deliver an offline-first Android app for Iteration-1 REQ subset (REQ-01, REQ-02.001..REQ-02.003, REQ-04.001..REQ-04.003, REQ-04.006, REQ-04.007, REQ-04.010, REQ-05.002..REQ-05.003, REQ-11.001) with no backend or web runtime dependency.
created_date: '2026-05-08 14:34'
target_date: null
---

## Overview

Deliver a fully offline mobile MVP with no backend or web runtime dependency.

## Goals

- Single-user flock workflows are fully offline.
- Genealogy works with local graph traversal and visualization.
- Observation-derived lifecycle reminders are automatic and reliable.
- Encrypted export/import backup works end to end.

## Scope

### Included
- REQ-01 Individual Management
- REQ-02.001..REQ-02.003 Genealogy and Parentage Graph
- REQ-04.001..REQ-04.003 Observations and Batch Entry
- REQ-04.006 Confirmed Birth Follow-Up
- REQ-04.007 Chronological Journal and Attachments
- REQ-04.010 Key Care and Reproduction Reminders
- REQ-05.002..REQ-05.003 Calendar Predicted Events and Reminders
- REQ-11.001 Mobile Create/Update Workflows

### Excluded
- Any requirements outside the included REQ subset
- Multi-user data sharing
- Backend and web runtime dependencies

## Success Criteria

- [ ] All Milestone 1 tasks completed and accepted
- [ ] End-to-end offline workflow passes
- [ ] Included REQ subset acceptance signed off against TASK-0027 mapping
- [ ] Performance targets met for 1,000+ individuals

## Tasks

- TASK-0002 - M1 Android Workspace Bootstrap
- TASK-0003 - M2 Domain Model Contract
- TASK-0004 - M3 Room Schema and Migration Strategy
- TASK-0005 - M4 Local Security Baseline
- TASK-0006 - M5 Repository Layer
- TASK-0007 - M6 Lifecycle Rules Engine
- TASK-0008 - M7 Genealogy Traversal Service
- TASK-0009 - M8 Individuals Feature UI
- TASK-0010 - M9 Observations and Batch UI
- TASK-0011 - M10 Genealogy UI
- TASK-0012 - M11 Calendar and Journal UI
- TASK-0013 - M12 Local Attachment Handling
- TASK-0014 - M13 Encrypted Backup Export and Import
- TASK-0015 - M14 Test Harness and Quality Gates
- TASK-0016 - M15 MVP Acceptance Audit
- TASK-0028 - M16-Genealogy-Rendering-REQ-Compliance
- TASK-0029 - M17-Birth-Follow-Up-and-Reminder-Types-Completion
- TASK-0030 - M18-Journal-Listing-and-Attachment-Criteria-Completion
- TASK-0031 - M19-Calendar-Predicted-Events-and-Reminders-Completion
- TASK-0032 - M20-Mobile-Field-Create-Update-Completion
