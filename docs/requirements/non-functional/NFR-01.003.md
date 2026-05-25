# NFR-01.003 Data Persistence

## Description

Persistent application data must reside outside container images for durability and portability. Database files must be stored on persistent volumes, object storage must use cloud provider or external storage, and container restarts must not result in data loss.

## Rationale

Containerized workloads are ephemeral by nature; separating data from compute ensures that application data survives container failures, updates, and rescheduling. Addresses the durability and data integrity quality attributes.

## Verification Method

Verified by restarting containers and confirming all application data remains intact and accessible. Persistent volume (PV/PVC) configuration must be validated for each database and object storage service.
