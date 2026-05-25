# Technical Stack

### Backend
- Framework: Spring Boot (Java) for robustness and security.
- Persistance:
  - Relational: PostgreSQL for structured data.
  - File Storage: AWS S3 for photos and documents.

### Frontends
#### Web applications
- Framework: Vue.js.

#### Mobile Application
- Android/Kotlin
  - Material Design 3 and Jetpack Compose

### Security Strategy
- Authentication through OAuth2/OpenID Connect with Keycloak.
- Data protection through encryption and anonymization by design.

### Deployment and Operations
- Containerization with Podman and Kubernetes.
- Compatible with local deployment, public cloud, or private cloud.
- Persistence provided through storage outside containers.
- Automated backups available locally and optionally in the cloud.
- Kubernetes used for scaling.
