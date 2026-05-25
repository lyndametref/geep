# DEPLOY-006 CI/CD Pipeline Standards

**Applies to:** mobile, backend, web

## Guideline

All builds, tests, and deployments must be automated through a repeatable CI/CD pipeline. No manual deployment steps.

## Rules

### Every Pipeline Must
1. **Checkout & setup**: clone repo, install dependencies.
2. **Lint & static analysis**: run formatter check, lint, SAST (Detekt for Kotlin, ESLint for Vue, etc.). Fail on new violations.
3. **Unit tests**: run all unit tests. Fail on failures.
4. **Dependency scan**: OWASP Dependency-Check or equivalent. Fail on critical/high CVEs with published fix.
5. **Build**: produce release artifact (APK/AAB for Android, JAR/WAR for backend, dist bundle for web).
6. **Integration tests** (backend): run against testcontainers.
7. **Sign** (mobile): sign APK/AAB with release keystore.
8. **Publish**: push artifact to artifact repository (Nexus, Docker registry, etc.).
9. **Deploy**: promote to staging, run smoke tests, then production.
