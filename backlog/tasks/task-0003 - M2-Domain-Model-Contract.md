---
id: TASK-0003
title: M2 Domain Model Contract
status: To Do
assignee: []
created_date: '2026-05-08 14:35'
updated_date: '2026-05-10 15:08'
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
Define the persistence-agnostic domain model contract for the mobile MVP and document it in `docs/domainModel.md` so downstream tasks can implement storage and rules without reinterpreting requirements.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [ ] #1 `docs/domainModel.md` exists and defines persistence-agnostic entities for FR-001 individual management, including stillborn support, optional-later BDTA assignment, sex/color, and alive/dead status.
- [ ] #2 The model captures parentage relationships required for genealogy (FR-002), including parent links between individuals.
- [ ] #3 The model captures observations and journal linkage required by FR-004, including attachment metadata references (for example photo/PDF attachment identity and association to journal entries).
- [ ] #4 `docs/domainModel.md` includes, at minimum: overview, context map, business object model (ER-style), and entity descriptions with key business rules.
- [ ] #5 Every modeled concept in `docs/domainModel.md` is traceable to existing requirements in `specs/REQUIREMENTS.md`; unresolved ambiguities are listed as explicit open questions.
<!-- AC:END -->

## Assumptions

- Task-0003 defines domain structure and terminology only; persistence implementation is handled later (for example in TASK-0004).
- Attachment handling in this phase is represented as domain metadata and associations, not storage implementation details.
- Domain coverage is limited to mobile MVP scope and required dependencies from current specifications.
- To keep FR-003 traceable without over-specifying deduction rules, the domain model should include a placeholder `TraitAssessment` entity linked to an individual, with a trait identifier and optional phenotype/genotype values; detailed deduction logic remains deferred.

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 `docs/domainModel.md` is updated and peer-reviewed against `specs/REQUIREMENTS.md`.
- [ ] #2 Acceptance criteria in this ticket are verifiably satisfied by the documented model.
- [ ] #3 Follow-up impacts or clarifications for dependent tickets (for example TASK-0004) are identified.
<!-- DOD:END -->
