# NFR-02.002 Field and Web Workflow Synchronization

## Description

The platform must support parallel field workflows (mobile) and office workflows (web) operating on the same dataset with eventual consistency. Mobile app and web app must share the same backend API. Data changes must synchronize between mobile and web within 5 minutes. Conflict resolution must handle offline edits on mobile. Full traceability of data changes must be maintained.

## Rationale

Field workers and office staff operate concurrently on overlapping data sets. Mobile devices may have intermittent connectivity, so offline-capable workflows with eventual consistency are essential. Addresses the availability, consistency, and usability quality attributes.

## Verification Method

Verified by performing concurrent edits on mobile (offline) and web, then confirming synchronization completes within 5 minutes after mobile reconnects. Conflict scenarios must be resolved without data loss. Change history must trace each mutation to its source.
