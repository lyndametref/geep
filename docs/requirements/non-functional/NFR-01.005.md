# NFR-01.005 Open Source Technology Stack

## Description

The system must use an open source software stack with no mandatory proprietary dependencies, including Google services. No proprietary component or Google-hosted service, API, or SDK may be required for core system functionality, deployment, or operation.

## Rationale

Ensures vendor independence, long-term maintainability, and cost control. Avoids lock-in to proprietary platforms that may impose licensing costs, usage restrictions, or discontinuation risks. Google services in particular may be unavailable, restricted, or blocked in certain regions where farming operations occur, creating unacceptable operational risk. Addresses the maintainability, portability, availability, and autonomy quality attributes.

## Verification Method

Verified by auditing all technology dependencies and confirming that every component in the critical path (runtime, database, storage, messaging, orchestration) has a viable open source option. Dependency tree of all applications (backend, web, mobile) must confirm no Google service SDK, API, or library is a mandatory dependency. Functional testing must confirm all core workflows operate without Google service access.
