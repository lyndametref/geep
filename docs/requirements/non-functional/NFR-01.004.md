# NFR-01.004 Automated Backup and Recovery

## Description

Backup processes must execute automatically and must support both local and cloud environments. Cloud backup is optional but must be supported when configured. Backup jobs must execute on a defined schedule (daily or better). Local backup must be stored on network-attached storage (NAS) or external media. Cloud backup must offer optional integration with cloud backup services. Backup retention policy must be enforced with a minimum of 30 days of full backups. Recovery Time Objective (RTO) must be less than 4 hours. Recovery Point Objective (RPO) must be less than 24 hours.

## Rationale

Protects against data loss from hardware failures, accidental deletions, or disasters. Defines clear recovery expectations to minimize business impact. Addresses the reliability, durability, and disaster recovery quality attributes.

## Verification Method

Verified by running a recovery drill that measures actual time to restore (RTO) and data loss (RPO) from the most recent backup. Backup logs must confirm daily execution and retention compliance. Cloud backup integration, if configured, must be tested separately.
