---
id: MILESTONE-1
title: Iteration 1 - Mobile-Only MVP
status: Active
description: Deliver an offline-first Android app covering REQ-01, REQ-02.001-02.003, REQ-04.001-04.003, REQ-04.005, REQ-04.006, REQ-13.006, REQ-05.002-05.003, REQ-11.001 with no backend or web runtime dependency.
created_date: '2026-05-26'
target_date: null
---

## Overview

Deliver a fully offline mobile MVP with no backend or web runtime dependency. The app is a single-user, offline-first Android application using Room for local storage, Android Keystore for encryption, and Jetpack libraries for UI.

## Goals

- Single-user flock workflows (individuals, observations, journal) are fully offline.
- Genealogy works with local graph traversal and visualization.
- Observation-derived lifecycle reminders are automatic and reliable.
- Confirmed births trigger lamb-record creation and weaning scheduling.
- Encrypted backup/export works end-to-end.
- Zero UX friction security — Keystore-backed encryption with no manual security steps.

## Scope

### Included
- REQ-01 Individual Management (01.001–01.008)
- REQ-02.001–02.003 Genealogy and Parentage Graph
- REQ-04.001–04.003 Observations and Batch Entry
- REQ-04.005 Confirmed Birth Follow-Up
- REQ-04.006 Chronological Journal and Attachments
- REQ-13.006 Key Care and Reproduction Reminders
- REQ-05.002–05.003 Calendar Predicted Events and Reminders
- REQ-11.001 Mobile Create/Update Workflows

### Non-Functional Requirements (implicitly applicable)
- NFR-03.001 Long-Term Data Traceability
- NFR-03.002 Workflow Independence
- NFR-04.001 Zero UX Friction Security
- NFR-04.003 Data Encryption (local)
- NFR-05.002 Database Consistency

### Excluded
- REQ-03 Phenotype/Genotype
- REQ-06 Medication Management
- REQ-07 Calculators
- REQ-08 Cheat Sheets
- REQ-09 (all)
- REQ-10 Pasture Management
- REQ-12 Multi-user Data Sharing
- REQ-14 Financial Tracking
- REQ-15 Custom Types
- Backend and web runtime (Iteration 2)
- Multi-user data sharing

## Success Criteria

- [ ] All Milestone 1 tasks completed and accepted
- [ ] End-to-end offline workflow passes (create individual → observe → journal → genealogy → calendar)
- [ ] Included REQ subset acceptance signed off
- [ ] All 17 tasks (GEEP-0001 through GEEP-0017) are in "Done" status

## Tasks

- GEEP-0001 - M1 Android Workspace Bootstrap
- GEEP-0002 - M1 Mobile App Module Architecture
- GEEP-0003 - M1 Core Model Implementation
- GEEP-0004 - M1 Database Schema Design & Room Setup
- GEEP-0005 - M1 Repository Layer
- GEEP-0006 - M1 Business Rules Engine
- GEEP-0007 - M1 Genealogy Traversal Service
- GEEP-0008 - M1 Local Security Baseline
- GEEP-0009 - M1 Individuals Feature UI
- GEEP-0010 - M1 Observations & Batch Entry UI
- GEEP-0011 - M1 Calendar View & Reminders UI
- GEEP-0012 - M1 Genealogy View UI
- GEEP-0013 - M1 Journal & Attachments UI
- GEEP-0014 - M1 Confirmed Birth Follow-Up
- GEEP-0015 - M1 Encrypted Backup Export/Import
- GEEP-0016 - M1 Test Harness & Quality Gates
- GEEP-0017 - M1 MVP Acceptance Audit
