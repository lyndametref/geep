# DEPLOY-004 Data Persistence & Volume Management

**Applies to:** backend, mobile

## Guideline

Persistent application data must reside outside container images for durability and portability. Container restarts must not cause data loss. Backup volumes must be separate from data volumes.

