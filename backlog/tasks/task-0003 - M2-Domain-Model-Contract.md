---
id: TASK-0003
title: M2 Domain Model Contract
status: To Do
assignee: []
created_date: '2026-05-08 14:35'
updated_date: '2026-05-10 15:56'
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
- [ ] #1 `docs/domainModel.md` exists and defines persistence-agnostic entities for FR-001 individual management, including: unique internal identifier, optional-later BDTA assignment, birth/death dates (when available), sex, color, parents, alive/dead status,and portrait reference concept.
- [ ] #2 The model defines genealogy relationships required by FR-002 as graph-compatible parentage links between individuals (at minimum sire/dam relationship semantics).
- [ ] #3 The model defines FR-004 observation concepts, including: observation type, observation date/time, affected individual(s), treatment metadata (dose and quarantine period when relevant), derived event/reminder linkage, chronological journal entry linkage, and attachment metadata reference (for example photo/PDF identity and association to journal entries).
- [ ] #4 `docs/domainModel.md` includes all required sections: Overview, Context Map, Business Object Model (ER-style), Entity Catalog, Business Rules, and Requirement Traceability Matrix.
- [ ] #5 Requirement Traceability Matrix maps every modeled concept to at least one requirement identifier in `specs/REQUIREMENTS.md` (FR-001, FR-002, FR-004, and FR-003 placeholder coverage where applicable).
- [ ] #6 Business Rules explicitly define model-level decisions for currently unspecified MVP constraints: parentage cardinality validation policy, batch-group value policy, quarantine period representation policy, and portrait-selection cardinality policy.
- [ ] #7 `docs/domainModel.md` has no unresolved `Open Questions` section for MVP scope; any former open question is either resolved in Business Rules or recorded as a follow-up in this ticket.
<!-- AC:END -->

## Assumptions

- Task-0003 defines domain structure, terminology, and business constraints only; technical  implementation is handled later (for example TASK-0004).
- Attachment handling in this phase is represented as metadata and associations only; binary storage strategy is out of scope (handled in later attachment-focused work).
- Domain coverage is limited to mobile MVP scope defined in milestone 1 (FR-001, FR-002, FR-004).
- FR-003 is represented as a traceable placeholder capability only (for example `TraitAssessment` linked to an individual with trait identifier and optional phenotype/genotype fields); deduction algorithms are out of scope.
- Calendar rendering and UX behavior are out of scope; only domain-level derived event/reminder concepts required by FR-004 are in scope.
- Parentage cardinality constraints are not mandated by requirements in MVP scope; the domain contract must therefore represent links and roles without enforcing additional cardinality rules beyond modeled role semantics.
- Batch-group selection taxonomy is not specified in requirements; group values are represented as domain metadata and are not constrained to a controlled vocabulary in this ticket.
- Quarantine metadata is represented as a two period value per treatment detail in MVP scope; one for meat and one for milk.
- Portrait selection is optional per individual in MVP scope; the domain contract supports zero or one active portrait reference and does not require mandatory portrait assignment.

## Dependencies

- TASK-0001 provides the MVP scope contract used as source guardrails for this modeling work.
- TASK-0004, TASK-0006, TASK-0007, TASK-0009, TASK-0010, TASK-0011, and TASK-0012 consume this domain contract and must not redefine core entities or relationships without updating this ticket output.

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 `docs/domainModel.md` is completed with all sections required by AC #4.
- [ ] #2 All assumptions in this ticket are represented consistently in `docs/domainModel.md` (no conflicting rule statements).
- [ ] #3 A reviewer can map each AC item in this ticket to explicit content in `docs/domainModel.md` without inference.
- [ ] #4 Any required follow-up ticket impacts are recorded directly in this ticket under a `Follow-ups` section (or explicitly marked `None`).
- [ ] #5 No content in this ticket conflicts with `specs/REQUIREMENTS.md`, `specs/ARCHITECTURE.md`, or milestone-1 scope.
<!-- DOD:END -->

## Follow-ups

- None at ticket refinement time.
