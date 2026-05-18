# Architecture: Ovine Flock Management Software

## Introduction and Goals

### Vision
Create an applications ecosystem to support seamlessly the management of an ovine flock, and answer all legal requirements.

### Main Goals
- Centralize management of individual data (genealogy, health, observations).
- Automate reminders and events related to life cycles (births, weaning, treatments).
- Facilitate genetic analysis (colors, parentage) and health traceability.
- Enable flexible deployment (local, public/private cloud) with data persistence.

## Architecture Constraints
- Open Source.
- No dependency on Google services.
- Compliance with SSDF, OWASP, BSA, and SAFECode guidelines.

## Context and Scope

### Target Audience
- Sheep owners both professional and hobbyists.

### Scope
- Management of individual animal records.
- Genealogy and parentage visualization.
- Phenotype and genotype deduction.
- Observation tracking and automated lifecycle events.
- Calendar-based planning and reminders.
- Medication management.
- Integrated calculators and practical references.

## Solution Strategy

### Main Building Blocks
- **Mobile Application**: Collects information on the field with offline capability
- **Web Application**: Visualizes data and allows correction/completion of field-collected data
- **Server Backend**: Consolidates data and distributes it across multiple authenticated users

### Technical Architecture

#### Backend
- Framework: Spring Boot (Java) for robustness and security.
- Persistance:
  - Graph: Neo4j for genealogy.
  - Relational: PostgreSQL for structured data.
  - File Storage: AWS S3 for photos and documents.

#### Frontends
##### Web applications
- Framework: Vue.js.

##### Mobile Application
- Android/Kotlin
  - Material Design 3 and Jetpack Compose

#### Security Strategy
- Authentication through OAuth2/OpenID Connect with Keycloak.
- Data protection through encryption and anonymization by design.
- Alignment with SSDF, OWASP, BSA, and SAFECode practices.

#### Deployment and Operations
- Containerization with Podman and Kubernetes.
- Compatible with local deployment, public cloud, or private cloud.
- Persistence provided through storage outside containers.
- Automated backups available locally and optionally in the cloud.
- Kubernetes used for scaling.


