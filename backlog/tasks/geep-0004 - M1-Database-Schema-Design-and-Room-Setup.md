---
id: GEEP-0004
title: M1 Database Schema Design & Room Setup
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, database]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003]
ordinal: 4
---

## Description

Design and implement the Room database layer. Create Room entities (with @Entity annotations), DAOs, type converters, database class, and migration strategy. Use `docs/specs/mobile-database-schema.md` and the core model as reference. This is the `:core:database` module.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Room entities are created for all tables: individuals, records, observations, interventions, future_events, predicted_events, planned_tasks, waiting_delays, attachments with proper @Entity, @PrimaryKey, @ForeignKey annotations
- [ ] #2 DAOs are implemented for all entities with CRUD operations plus: query individuals by ID, list all, search by name/officialId, filter by sex/age; query records by individual chronologically; query attachments by record
- [ ] #3 Type converters are implemented for LocalDate, LocalDateTime (UTC), enums (Sex, RecordType, PredictionStatus, TaskStatus, DelayStatus), and JSON content fields
- [ ] #4 RoomDatabase class is configured with all entities, type converters, and migration strategy (destructive migration for MVP)
- [ ] #5 Database schema matches the canonical `docs/specs/mobile-database-schema.md` with proper indexes on foreign key columns
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Database compiles and schema export file validates
- [ ] #2 All DAO queries are unit-tested against an in-memory Room database
- [ ] #3 Type converter tests pass for all custom types
<!-- DOD:END -->
