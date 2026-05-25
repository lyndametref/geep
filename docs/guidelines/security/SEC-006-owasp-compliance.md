# SEC-006 OWASP Compliance

**Applies to:** mobile, backend, web

## Guideline

All application code must address the OWASP Top 10 vulnerabilities relevant to the platform.

### All Platforms
- Input validation: all user-facing text fields must validate length, character set, and expected format. Reject or sanitize malformed input.
- No hardcoded credentials, API keys, tokens, or passwords in source code. Encryption keys live in platform keystores — never in source.
- SQL injection prevention: all database queries must use parameterized queries (`@Query` with `:param` in Room; `PreparedStatement` / JPA parameter binding on backend). Raw queries require explicit security review.
- Output encoding applied consistently to prevent XSS.

### Backend & Web
- CSRF protection implemented for all state-changing operations.
- Security headers configured: Content-Security-Policy, X-Frame-Options, X-Content-Type-Options, Strict-Transport-Security.
- Secure random ID generation: use `java.security.SecureRandom` (Java) / `crypto.randomBytes` (Node.js) for all generated identifiers.

### Mobile
- Debug-build detection: app must detect debug-build mode at startup in release builds and terminate with a clear message. Runtime checks via `BuildConfig.DEBUG` or `ApplicationInfo.flags`.
- Log sanitization: no PII or encryption material written to Logcat in release builds.
- Deferred for MVP: certificate pinning, CSRF (no network layer yet).
