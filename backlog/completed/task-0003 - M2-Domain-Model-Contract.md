---
id: TASK-0003
title: M2 Domain Model Contract
status: Done
assignee: []
created_date: '2026-05-08 14:35'
updated_date: '2026-05-11 10:49'
labels:
  - mobile
  - domain
milestone: Iteration 1 Mobile-Only MVP
dependencies:
  - TASK-0001
ordinal: 1000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Define the persistence-agnostic domain model contract for the mobile-only MVP scope and document it in `docs/domainModel.md` so downstream tasks can implement storage, repositories, rules, and UI without reinterpreting requirements.

Scope for this ticket is limited to concepts required by FR-001, FR-002, and FR-004 (with FR-003 represented as a placeholder capability only, as specified in requirements). The ticket does not include persistence schema design, migration logic, or implementation code.

This ticket must produce a domain model with no unresolved decisions left open for MVP execution. Any decisions not defined in `specs/REQUIREMENTS.md` must be explicitly captured as assumptions in this ticket and reflected in `docs/domainModel.md`.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [x] #1 `docs/domainModel.md` exists and defines persistence-agnostic entities for FR-001 individual management, including: unique internal identifier, optional-later BDTA assignment, birth/death dates (when available), sex, color, parents, alive/dead status,and portrait reference concept.
- [x] #2 The model defines genealogy relationships required by FR-002 as graph-compatible parentage links between individuals (at minimum sire/dam relationship semantics).
- [x] #3 The model defines FR-004 observation concepts, including: observation type, observation date/time, affected individual(s), treatment metadata (dose and quarantine period when relevant), derived event/reminder linkage, chronological journal entry linkage, and attachment metadata reference (for example photo/PDF identity and association to journal entries).
- [x] #4 `docs/domainModel.md` includes all required sections: Overview, Context Map, Business Object Model.
- [x] #5 Key Business Rules explicitly define model-level decisions for currently unspecified MVP constraints: parentage cardinality validation policy, batch-group value policy, quarantine period representation policy, and portrait-selection cardinality policy.
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [x] #1 `docs/domainModel.md` is completed with all sections required by AC #4.
- [x] #2 All assumptions in this ticket are represented consistently in `docs/domainModel.md` (no conflicting rule statements).
- [x] #3 No content in this ticket conflicts with `specs/REQUIREMENTS.md`, `specs/ARCHITECTURE.md`, or milestone-1 scope.
<!-- DOD:END -->
