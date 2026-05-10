---
id: MILESTONE-2
title: Iteration 2 Backend + Web + Sync
status: Active
description: Introduce shared backend and web application, migrate mobile local data, and enable multi-user synchronization.
created_date: '2026-05-08 14:34'
target_date: null
---

## Overview

Introduce the server-backed platform: Spring Boot backend, Vue.js web, synchronization layer, and multi-user collaboration.

## Goals

- First-sync migration runs without data loss.
- Web and mobile converge within target synchronization window.
- Authentication, authorization, and audit controls are enforced.
- Platform supports FR-011 and FR-012 multi-user requirements.

## Scope

### Included
- Backend: Spring Boot + PostgreSQL + Neo4j
- Frontend: Vue.js web UI
- Synchronization: Mobile-to-backend sync adapter
- Multi-user: Auth (Keycloak), RBAC, audit logging
- Migration: First-sync from mobile MVP to server

### Excluded
- Mobile app hardening for iteration 2 (focus on sync)
- Advanced features (FR-003 phenotype, FR-005+ features)

## Success Criteria

- [ ] All 9 tasks completed and accepted
- [ ] First-sync migration successful with zero data loss
- [ ] Mobile and web converge and remain consistent
- [ ] FR-011 and FR-012 acceptance checks pass
- [ ] OWASP and SSDF compliance validated

## Tasks

- TASK-0017 - P0 Sync and Migration Contract
- TASK-0018 - P1 Backend Bootstrap
- TASK-0019 - P2 Auth and Access Control
- TASK-0020 - P3 API for Individuals and Observations
- TASK-0021 - P4 Genealogy Graph Backend
- TASK-0022 - P5 Web App Core Views
- TASK-0023 - P6 Mobile Sync Adapter
- TASK-0024 - P7 First Migration Execution
- TASK-0025 - P8 Shared Multi-user Validation
