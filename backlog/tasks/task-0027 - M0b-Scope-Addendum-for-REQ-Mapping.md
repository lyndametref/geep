---
id: TASK-0027
title: M0b Scope Addendum for REQ Mapping
status: Done
assignee:
  - '@github-copilot'
created_date: '2026-05-14 19:17'
updated_date: '2026-05-14 20:34'
labels:
  - planning
  - scope
  - traceability
milestone: Iteration 1 Mobile-Only MVP
dependencies: []
ordinal: 2000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Document the authoritative REQ-based scope mapping after requirement format split and lock iteration-1 minimal subset decisions without rewriting archived scope task content.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [x] #1 Defines Iteration-1 included scope as REQ-01, REQ-02.001..REQ-02.003, REQ-04.001..REQ-04.003, REQ-04.006,REQ-04.007,REQ-04.010,REQ-05.002..REQ-05.003 and REQ-11.001
<!-- AC:END -->

## Implementation Plan

<!-- SECTION:PLAN:BEGIN -->
1. Update milestone description to REQ-based wording while preserving offline/no-backend constraints.
2. Replace Included scope with exact TASK-0027 REQ subset.
3. Rewrite Excluded scope to avoid REQ-11.001 contradiction and keep backend/web exclusions.
4. Update success criteria from FR sign-off to REQ sign-off against TASK-0027 mapping.
5. Replace fixed 15-task criterion with dynamic milestone-task completion wording.
6. Verify task list unchanged in this pass.
<!-- SECTION:PLAN:END -->

## Implementation Notes

<!-- SECTION:NOTES:BEGIN -->
Updated Milestone 1 file to replace FR-based scope/sign-off wording with TASK-0027 REQ subset mapping.

Adjusted success criteria to dynamic milestone task count wording and REQ sign-off against TASK-0027.

Kept Milestone 1 task list unchanged in this pass per user request.

Created TASK-0028..TASK-0032 to cover uncovered REQ clusters: REQ-02.002/02.003, REQ-04.006/04.010, REQ-04.007, REQ-05.002/05.003, and REQ-11.001.

Updated Milestone 1 task list to include TASK-0028, TASK-0029, TASK-0030, TASK-0031, and TASK-0032.

Reprioritized Milestone 1 To Do tasks with explicit priority and ordinal sequencing; added needs-refinement label to enforce refine-before-implementation workflow for TASK-0004..TASK-0016 and TASK-0028..TASK-0032.
<!-- SECTION:NOTES:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [x] #1 Tests pass
- [x] #2 Documentation updated
- [x] #3 No regressions introduced
<!-- DOD:END -->
