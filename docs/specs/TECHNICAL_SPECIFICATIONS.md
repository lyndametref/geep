# Technical Specifications: Non-Functional Requirements

## Technologies and Tools

### Platform Constraints
- Open source software stack with no mandatory proprietary dependencies.
- No required dependencies on Google services.
- Compliance alignment with SSDF (Secure Software Development Framework), OWASP (Open Web Application Security Project), BSA (Build Security Assurance), and SAFECode guidelines.

### Backend Technologies
| Component | Technology | Rationale |
|-----------|-----------|-----------|
| Application Framework | Spring Boot (Java) | Enterprise robustness and security support |
| Graph Database | Neo4j | Optimized for genealogy and relationship data |
| Relational Database | PostgreSQL | Structured data persistence and ACID compliance |
| Object Storage | AWS S3 (or equivalent) | Scalable photo and document storage |

### Frontend Technologies
| Layer | Technology | Purpose |
|-------|-----------|---------|
| Web Application | Vue.js | Interactive web interface |
| Mobile Platform | Android/Kotlin with Material Design 3 & Jetpack Compose | Field workflows on mobile devices |
| Graph Visualization | Cytoscape.js | Genealogy graph rendering and interaction |
| Icons & Graphics | SVG (procedurally rendered) | Scalable vector graphics |

### Deployment and Operations Tooling
| Component | Technology | Purpose |
|-----------|-----------|---------|
| Containerization | Podman | Container runtime and orchestration |
| Orchestration | Kubernetes | Multi-environment orchestration |
| Deployment Options | Local, Public Cloud, Private Cloud | Flexible deployment strategy |

---

## Performance Requirements

**NFR-001: Multi-Environment Deployment**
- Requirement: The system must support flexible deployment across local (Podman), public cloud, and private cloud environments without core functionality degradation.
- Acceptance Criteria:
  - Same codebase deploys to all three environments
  - No environment-specific code branches for core features
  - Configuration externalized via environment variables or config maps

**NFR-002: Kubernetes Scalability**
- Requirement: The system must enable horizontal scaling through Kubernetes orchestration.
- Acceptance Criteria:
  - Backend services are stateless and horizontally scalable
  - Database connections are pooled and managed centrally
  - Load balancing distributes requests across replicas
  - Scaling can be automated via HPA (Horizontal Pod Autoscaler)

**NFR-003: Data Persistence**
- Requirement: Persistent application data must reside outside container images for durability and portability.
- Acceptance Criteria:
  - Database files stored on persistent volumes
  - Object storage uses cloud provider or external storage
  - Container restart does not result in data loss

**NFR-004: Automated Backup and Recovery**
- Requirement: Backup processes must execute automatically and support execution in local and optional cloud environments.
- Acceptance Criteria:
  - Backup jobs execute on defined schedule (daily or better)
  - Local backup stored on network-attached storage (NAS) or external media
  - Cloud backup optional integration with cloud backup services
  - Backup retention policy enforced (minimum: 30 days full backups)
  - RTO: < 4 hours (recovery target time)
  - RPO: < 24 hours (recovery point objective)

**NFR-005: Multi-User Concurrent Access**
- Requirement: The backend must support concurrent authenticated access by multiple users without data corruption or service degradation.
- Acceptance Criteria:
  - Session management handles 100+ concurrent users
  - Database locking prevents concurrent write conflicts
  - Performance degradation < 10% at peak concurrent load
  - Connection pooling configured for backend services

**NFR-006: Field and Web Workflows**
- Requirement: The platform must support parallel field workflows (mobile) and office workflows (web) operating on the same dataset with eventual consistency.
- Acceptance Criteria:
  - Mobile app and web app share the same backend API
  - Data changes synchronize between mobile and web within < 5 minutes
  - Conflict resolution handles offline edits on mobile
  - Full traceability of data changes maintained

**NFR-007: Long-Term Data Traceability**
- Requirement: The platform must maintain persistent, auditable records of health, genealogy, and observational data with historical changesets.
- Acceptance Criteria:
  - All mutations logged with timestamp, user ID, and change description
  - Historical view available for any data entity
  - Data retention: minimum 10 years per regulatory requirements
  - Audit logs immutable and tamper-evident

**NFR-008: Workflow Independence**
- Requirement: The system must enable continuous flock management workflows without dependency on external services (specifically Google services).
- Acceptance Criteria:
  - All critical workflows functional in offline/disconnected mode
  - Offline changes sync when connectivity restored
  - No feature degradation when external services unavailable

---

## Security Requirements
**NFR-019: zero UX friction**
- Usability is key for field work, security is efficient but invisible.
- Security controls operate with  zero UX friction: users are never asked to enter a passphrase or PIN, configure encryption settings, or perform manual security actions for data access
- The only security element acceptable is the login between the different elements of the system (login t0 access the backend), and it must be minimised.

**NFR-009: Authentication and Identity Management**
- Requirement: All user access must authenticate via OAuth2/OpenID Connect using Keycloak as the identity provider.
- Acceptance Criteria:
  - Authentication redirects users to Keycloak
  - JWT tokens issued and validated for API requests
  - Session timeout enforced (default: 30 minutes of inactivity)
  - Multi-factor authentication (MFA) supported

**NFR-010: Data Encryption**
- Requirement: Sensitive data must be encrypted both in transit and at rest.
- Acceptance Criteria:
  - TLS 1.2+ required for all network communication
  - Database encryption enabled at storage layer (PostgreSQL & Neo4j)
  - Object storage uses server-side encryption (SSE-S3 or KMS)
  - API keys and secrets stored in encrypted vaults

**NFR-011: Privacy by Design – Anonymization**
- Requirement: The platform must implement anonymization capabilities to protect personal data and support privacy-compliant operations.
- Acceptance Criteria:
  - Personally Identifiable Information (PII) can be selectively masked in reports
  - De-identification functions available for analytics and testing
  - Data minimization: only necessary data collected
  - Privacy impact assessments performed for new features

**NFR-012: SSDF Compliance (Secure Software Development Framework)**
- Requirement: Development practices must align with NIST SSDF guidance.
- Acceptance Criteria:
  - Build automation prevents manual package deployment
  - All code changes subject to peer review before merge
  - Automated security scanning (SAST, dependency checks) in CI/CD
  - Cryptographic signing of releases

**NFR-013: OWASP Compliance**
- Requirement: Security controls must address OWASP Top 10 vulnerabilities.
- Acceptance Criteria:
  - Input validation and output encoding applied consistently
  - No hardcoded credentials in codebase
  - SQL injection prevention via parameterized queries
  - CSRF protection implemented for state-changing operations
  - Security headers configured (CSP, X-Frame-Options, etc.)

**NFR-014: BSA & SAFECode Compliance**
- Requirement: Development and deployment practices must align with BSA and SAFECode secure development standards.
- Acceptance Criteria:
  - Security training mandatory for all developers
  - Threat modeling performed for new features
  - Vulnerability remediation SLA: critical ≤ 48 hours, high ≤ 1 week
  - Third-party component tracking and update management

---

## Reliability & Availability Requirements

**NFR-015: Service Availability**
- Requirement: Production services must maintain high availability.
- Acceptance Criteria:
  - Target uptime: 90% (allowing ~72 hours/month downtime)
  - Automated health checks every 5 minutes
  - Automatic failover for stateless services
  - Graceful degradation when dependent services fail

**NFR-016: Database Consistency**
- Requirement: Data consistency must be maintained across distributed components.
- Acceptance Criteria:
  - ACID compliance for PostgreSQL transactions
  - Eventual consistency model for Neo4j graph updates (< 5 min)
  - Conflict resolution strategy documented and tested

---

## Maintainability & Operations Requirements

**NFR-017: Automated Deployment**
- Requirement: The system architecture must support safe deployment of updates.
- Acceptance Criteria:
  - Rollback capability within 5 minutes
  - Deployment automation via CI/CD pipeline

**NFR-018: Operational Monitoring**
- Requirement: Production systems must be observable and monitorable.
- Acceptance Criteria:
  - Structured logging (JSON format) for all services
  - Metrics exported for CPU, memory, disk, network, database queries
  - Distributed tracing enabled for request flow visibility
  - Alert thresholds defined for SLA breaches

---

## Technical Glossary

| Term | Definition |
|------|-----------|
| **SSDF** | Secure Software Development Framework - NIST guidance for secure software development practices |
| **OWASP** | Open Web Application Security Project - industry standard for web application security |
| **BSA** | Build Security Assurance - software security best practices from the Business Software Alliance |
| **SAFECode** | Software Assurance Forum for Excellence in Code - guidelines for software security |
| **OAuth2/OpenID Connect** | Industry standards for authentication and authorization |
| **JWT** | JSON Web Token - stateless authentication token format |
| **RTO** | Recovery Time Objective - maximum acceptable time to restore service after failure |
| **RPO** | Recovery Point Objective - maximum acceptable data loss measured in time |
| **PII** | Personally Identifiable Information - data that can identify an individual |
| **SAST** | Static Application Security Testing - code analysis for vulnerabilities |
| **HPA** | Horizontal Pod Autoscaler - Kubernetes automatic scaling controller |
| **ACID** | Atomicity, Consistency, Isolation, Durability - database transaction properties |
| **GDPR** | General Data Protection Regulation - EU privacy regulation |

---

## Compliance Reference Guide

### SSDF & Secure Development
- Secure build and deployment pipelines (NFR-017)
- Threat modeling and risk assessment for features (NFR-012)
- Code review and security testing in CI/CD (NFR-012)
- Vulnerability tracking and remediation SLAs (NFR-014)

### OWASP & Web Security
- Injection prevention and defense mechanisms (NFR-013)
- Authentication and session management (NFR-009)
- Cross-Site Request Forgery (CSRF) protection (NFR-013)
- Security headers and content security policies (NFR-013)

### BSA & SAFECode
- Security training and awareness for team (NFR-014)
- Third-party component management and updates (NFR-014)
- Incident response and disclosure procedures (NFR-014)
- Cryptographic standards and key management (NFR-010)
