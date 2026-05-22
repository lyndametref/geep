# Database Migration Strategy

This document outlines the strategy for evolving the Geep Android application database schema using Room.

## Principles

1. **Data Preservation**: No migration should result in the loss of user data unless explicitly required by a breaking domain change.
2. **Forward Compatibility**: The schema should be designed to allow additive changes (new tables, new nullable columns) with minimal friction.
3. **Automated Testing**: Every migration must be accompanied by an automated test that verifies data integrity before and after the migration.
4. **Schema Export**: Room schema export is enabled to track schema history and provide a baseline for migration testing.

## Schema Overview

The schema uses Class Table Inheritance (CTI): each domain subtype has its own table with a foreign key to the parent table.

### Individual Management

`individuals` — Core entity for sheep identity, lifecycle, and lineage.

### Journaling

`records` — Base journal entry with common fields (id, timestamp, recordType, individualId, sourceRecordId). Each concrete record type has a dedicated subtype table:

`observations` — Weight, health observations, medical analysis results, reproduction events. FK → records.

`interventions` — Performed actions, care, treatment (shearing, hoof trimming, quarantine). FK → records.

`attachments` — Documentary evidence (photos, PDFs) metadata linked to a record. FK → records.

### Planning (Future Events)

`future_events` — Base table for planned/predicted/waiting events. Fields: recordId (PK, FK → records).

`predicted_events` — Probabilistic outcomes with earliest/latest date range (e.g. predicted birth). FK → future_events.

`planned_tasks` — Concrete upcoming actions with title, reminder, and due dates (e.g. weaning). FK → future_events.

`waiting_delays` — Delay intervals (e.g. quarantine withdrawal periods). FK → future_events.

## Migration Types

### 1. Automated Migrations (Room Auto-Migrations)
For simple additive changes like adding new tables or nullable columns, we prefer Room's `AutoMigration` feature (introduced in Room 2.4.0).

### 2. Manual Migrations
For complex changes (renaming columns, changing data types, restructuring tables), manual `Migration` objects must be implemented.

## Migration History

| Version | Description |
|---------|-------------|
| 1 | Initial schema: individuals, records + subtype tables (observations, interventions, future_events + predicted_events, planned_tasks, waiting_delays), attachments |

## Rollback Strategy
Android/Room does not support native "down" migrations. In case of a failed migration:
- During development/testing: Wipe data and start from a fresh database.
- Production: If a bug is found in a migration, a new "up" migration must be released to fix the state or restore data from a temporary "backup" table if one was created during the migration.

## Versioning
- Current schema version: `1`
- Increments: +1 for every schema change after MVP release.

## Schema History
Schema snapshots are located in `appAndroid/core-database/schemas/`. These files are under version control and must not be modified manually.
