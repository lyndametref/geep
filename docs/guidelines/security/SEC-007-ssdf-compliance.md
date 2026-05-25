# SEC-007 SSDF Compliance (NIST Secure Software Development Framework)

**Applies to:** mobile, backend, web

## Guideline

Development practices must align with NIST SSDF (Secure Software Development Framework) guidelines.

- Build automation: all release builds produced by repeatable CI/CD pipeline. No manual package deployment.
- Static analysis (SAST): run on every PR (Detekt for Kotlin, SonarQube or equivalent for Java). Fail build on new violations.
- Dependency scanning: automated scanning (OWASP Dependency-Check or equivalent) on every build. Fail on critical/high CVEs with published fix. Maintain a dependency inventory.
- Cryptographic signing: release builds must be signed with a dedicated release keystore. Debug builds use default debug keystore.
- ProGuard / R8: release builds must enable minification, obfuscation, and optimization. Keep rules maintained for reflection and serialization targets.

## Rationale

SSDF provides a framework for integrating security into every stage of the software development lifecycle, reducing the risk of vulnerabilities reaching production.
