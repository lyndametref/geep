---
id: TASK-0004
title: M3 Room Schema and Migration Strategy
status: Done
assignee:
  - '@agent'
created_date: '2026-05-08 14:35'
updated_date: '2026-05-22 08:02'
labels:
  - mobile
  - database
milestone: Iteration 1 Mobile-Only MVP
dependencies:
  - TASK-0003
priority: high
ordinal: 3000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Define the initial Room persistence contract for Iteration 1 using the TASK-0003 domain model, with a migration-ready foundation for future schema evolution.

Scope:
- Provide Room entities/relations and DAO CRUD for MVP persistence objects required by Iteration 1.
- Keep schema minimal at first release while preserving safe forward migrations.

Out of scope:
- TraitAssessment placeholder persistence (explicitly excluded from TASK-0004).
- Attachment binary/file storage implementation (handled by TASK-0013); only metadata references are in scope where needed.
- Feature-specific query projections/optimizations beyond core CRUD (future tasks extend schema/queries as needed).

Assumptions:
- Initial schema prioritizes simplicity and stable identifiers/relations so future migrations can evolve without data loss.
- Migration strategy is defined now for forward compatibility; concrete future migrations can be added incrementally.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [x] #1 Room schema covers Iteration 1 MVP persistence entities from TASK-0003, excluding TraitAssessment placeholder and excluding attachment binary storage.
- [x] #2 DAO layer provides core CRUD operations for the persisted entities in TASK-0004 scope.
- [x] #3 Room database versioning and schema export are configured so schema history is trackable for future migration work.
- [x] #4 Migration strategy is documented for future additive/evolutionary changes, including data-preservation and rollback expectations.
- [x] #5 Automated tests validate CRUD behavior and verify database open/upgrade path consistency for the current schema baseline.
<!-- AC:END -->

## Implementation Plan

<!-- SECTION:PLAN:BEGIN -->
Schema corrections applied per refinement request:

1. **observations**: Removed `observationType` column. The `content` column (now mandatory) holds a JSON string containing the type discriminator.
2. **interventions**: Renamed `interventionType` → `content` (mandatory JSON holding the type).
3. **records**: Added `sourceRecordId` (moved from `future_events`). Every record type can now reference a source record.
4. **future_events**: Stripped down to just `recordId PK`. Both `futureEventType` and `status` columns removed.
5. **predicted_events**: Added `FutureEventStatus status` and `string content`.
6. **planned_tasks**: Added `FutureEventStatus status` and `string* content`. Removed `title` (moved into content JSON).
7. **waiting_delays**: Added `FutureEventStatus status` and `string content`. Removed `elapsed` (derivable from `delayElapsedAt` and `now()`).
8. **Notes**: Updated to reflect the new content JSON docs and per-sub-type future event status.
<!-- SECTION:PLAN:END -->

## Implementation Notes

<!-- SECTION:NOTES:BEGIN -->
Schema corrections applied to docs/specs/mobile-database-schema.md.
<!-- SECTION:NOTES:END -->

## Final Summary

<!-- SECTION:FINAL_SUMMARY:BEGIN -->
Implemented Room schema Iteration 1 MVP: entities/DAOs for Individuals, Records (with CTI subtypes: Observations, Interventions, FutureEvents → PredictedEvents, PlannedTasks, WaitingDelays), and Attachments (metadata-only). Migration strategy documented at docs/specs/DATABASE_SCHEMA_MIGRATION_STRATEGY.md. Schema export enabled at v1. Robolectric unit tests verify DAO CRUD and database baseline integrity.
<!-- SECTION:FINAL_SUMMARY:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [x] #1 CRUD and database baseline tests pass in CI/local test harness.
- [x] #2 Schema/versioning and migration strategy documentation are updated and consistent with scope exclusions.
- [x] #3 No overlap is introduced with TASK-0013 attachment file-handling scope or future feature-specific query tasks.
<!-- DOD:END -->
