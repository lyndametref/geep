# DEPLOY-008 Release & Rollback Management

**Applies to:** backend, web, mobile

## Guideline

Deployments must be reversible.  Database migrations must be backward-compatible (additive changes only) to allow safe rollback of the application without rolling back the schema. Database migration on mobile must be forward-only (Room does not support down migrations). A broken migration must be fixed with a new "up" migration.


APK side-loading: hotfix distribution via updated APK. Keep previous version APK signed and available.

