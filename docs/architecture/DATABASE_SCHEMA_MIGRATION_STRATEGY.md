# Database Migration Strategy

This document outlines the strategy for evolving the Geep Android application database schema using Room.

## Principles

1. **Data Preservation**: No migration should result in the loss of user data unless explicitly required by a breaking domain change.
2. **Forward Compatibility**: The schema should be designed to allow additive changes (new tables, new nullable columns) with minimal friction.
3. **Automated Testing**: Every migration must be accompanied by an automated test that verifies data integrity before and after the migration.
4. **Schema Export**: Room schema export is enabled to track schema history and provide a baseline for migration testing.

## Migration Types

### 1. Automated Migrations (Room Auto-Migrations)
For simple additive changes like adding new tables or nullable columns, we prefer Room's `AutoMigration` feature (introduced in Room 2.4.0).

### 2. Manual Migrations
For complex changes (renaming columns, changing data types, splitting tables), manual `Migration` objects must be implemented.

## Rollback Strategy
Android/Room does not support native "down" migrations. In case of a failed migration:
- During development/testing: Wipe data and start from a fresh database.
- Production: If a bug is found in a migration, a new "up" migration must be released to fix the state or restore data from a temporary "backup" table if one was created during the migration.

## Versioning
- Initial schema version: `1`
- Increments: +1 for every schema change.

## Schema History
Schema snapshots are located in `appAndroid/core-database/schemas/`. These files are under version control and must not be modified manually.
