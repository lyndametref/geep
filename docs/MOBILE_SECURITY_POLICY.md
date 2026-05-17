# Mobile Application Security Policy — Local-Only Offline MVP

## Document Control

| Property | Value |
|----------|-------|
| **Version** | 1.0 |
| **Status** | Approved |
| **Applies to** | Geep Android application (local-only offline MVP, no backend, no Google services) |
| **Security posture** | Production-hardened from first release |
| **Last updated** | 2026-05-17 |

---

## 1. Scope

This policy covers the Geep Android application in its **local-only offline MVP** configuration:
- Single-device operation with no backend server
- No dependency on Google Play Services
- Room SQLite database for local persistence
- Local file storage for photo and document attachments
- Distribution via APK side-loading (not Play Store)
- Minimum SDK: 35 (Android 15)
- Data stored: animal health/genealogy records **and** owner PII (names, contact details)

---

## 2. Principles

### 2.1 Zero UX Friction

All security controls **must operate transparently** to the end user. The user must never be asked to:
- Enter a passphrase or PIN for data access
- Configure encryption settings
- Perform manual security actions

The device lock screen is the sole authentication gate. Security derives from the Android Keystore, which releases cryptographic keys only when the device is unlocked (`setUnlockedDeviceRequired(true)`). This provides encryption-at-rest protection without degrading the offline-first user experience.

### 2.2 Defence in Depth

Controls are layered: encryption at rest, input validation, build hardening, and dependency management. No single control is the sole line of defence.

### 2.3 Privacy by Design

Owner PII is collected only when necessary for the feature. Data minimisation is enforced at the feature-design stage.

---

## 3. NFR Applicability Mapping

The table below maps each security NFR from `TECHNICAL_SPECIFICATIONS.md` to the local-only offline MVP, with rationale.

| NFR | Title | Applies? | Rationale |
|-----|-------|----------|-----------|
| NFR-010 | Data Encryption | **Partial** | Encryption at rest applies (Room DB, SharedPreferences, file attachments). Encryption in transit (TLS) is N/A — no network communication in MVP. |
| NFR-011 | Privacy by Design | **Yes** | Owner PII is stored. Data minimisation and masking are required. |
| NFR-012 | SSDF Compliance | **Yes** | Build automation, peer review, SAST scanning, and cryptographic signing are all applicable to Android development. |
| NFR-013 | OWASP Compliance | **Partial** | Input validation, hardcoded-secret prevention, and SQLi prevention apply. CSRF and web security headers are N/A (no web component in MVP). |
| NFR-014 | BSA & SAFECode | **Yes** | Threat modelling, dependency tracking, and vulnerability SLA apply as process controls. |

---

## 4. Applicable Controls

### 4.1 NFR-010: Data Encryption at Rest

**Goal:** All persisted sensitive data is encrypted transparently using Android Keystore-backed keys.

| Asset | Control | Implementation |
|-------|---------|----------------|
| Room database | Full-database AES-256 encryption via SQLCipher | `SupportFactory` with `MasterKey` from Android Keystore |
| SharedPreferences | `EncryptedSharedPreferences` (AES-256 GCM) | Same `MasterKey` — reads/writes transparently encrypted |
| Photo / document attachments | `EncryptedFile` via AndroidX Security Crypto | Stream-level AES-256 encryption, same key derivation |
| Encryption key root | Android Keystore `MasterKey` | `MasterKey.Builder` with `setKeyScheme(AES256_GCM)` and `setUnlockedDeviceRequired(true)`. No biometric/PIN prompt. |

**Key lifecycle:**
- Key created once on first app launch
- Stored in Android Keystore (hardware-backed on devices with TEE/StrongBox)
- Never leaves secure hardware
- Automatically destroyed on app uninstall or device factory reset
- Inaccessible while device is locked

**Deferred from MVP:**
- File-level encryption key rotation (single-key model is acceptable for MVP)

---

### 4.2 NFR-011: Privacy by Design

**Goal:** Owner PII is collected minimally, handled consciously, and never exposed unnecessarily.

| Control | Implementation |
|---------|----------------|
| Data minimisation | Feature specs must enumerate every PII field with justification. Fields without a clear use case are excluded. |
| Masked display | PII fields (names, contact details) support a "masked" toggle in list views and reports. Masking shows initials or truncated values by default. |
| No analytics / tracking | MVP has zero telemetry, crash reporting, or usage analytics (in line with "no Google services" constraint). |
| Export hygiene | If data export is implemented, it must include an opt-out for PII fields. |

---

### 4.3 NFR-012: SSDF Compliance

**Goal:** Development practices meet NIST SSDF secure-development guidelines.

| Control | Implementation |
|---------|----------------|
| Build automation | Release builds produced by repeatable CI/CD pipeline. No manual package deployment. |
| Peer review | Every code change reviewed and approved before merge. |
| Static analysis (SAST) | Detekt (or equivalent) runs on every PR. Fail build on new violations. |
| Dependency scanning | OWASP Dependency-Check or Gradle `dependencyCheckAnalyze` on every build. Fail on critical/high CVEs with published fix. |
| Cryptographic signing | Release APK signed with a dedicated release keystore. Debug builds use the default debug keystore. |
| ProGuard / R8 | Release builds enable minification, obfuscation, and optimisation. Keep rules maintained for reflection and serialisation targets. |

---

### 4.4 NFR-013: OWASP Compliance (Mobile Subset)

**Goal:** Address OWASP Mobile Top 10 risks applicable to a local-only offline app.

| Control | Implementation |
|---------|----------------|
| Input validation | All user-facing text fields validate length, character set, and expected format. Reject or sanitise malformed input. |
| No hardcoded secrets | Zero secrets, API keys, tokens, or passwords in source code. Encryption keys live in Keystore — never in source. |
| SQL injection prevention | Room DAOs use parameterised queries (`@Query` with `:param`). Raw queries (`@RawQuery`) require explicit security review. |
| Debug-build protections | App must detect debug-build mode at startup in release builds and terminate with a clear message. Runtime checks via `BuildConfig.DEBUG` or `ApplicationInfo.flags`. |
| Log sanitisation | No PII or encryption material written to `Logcat` in release builds. |
| Secure random ID generation | Use `java.security.SecureRandom` for all generated IDs (e.g., animal UUIDs, attachment filenames). |

**Deferred from MVP (N/A for local-only):**
- CSRF protection (no web context)
- Security headers / CSP (no web context)
- Certificate pinning (no network in MVP)

---

### 4.5 NFR-014: BSA & SAFECode Compliance

**Goal:** Development and deployment follow BSA and SAFECode secure-software best practices.

| Control | Implementation |
|---------|----------------|
| Security awareness | All contributors read this policy. Threat-modelling orientation included in onboarding. |
| Threat modelling | Lightweight threat model produced for every new feature. STRIDE-per-element is the recommended methodology. |
| Vulnerability SLA | Critical: remediate within 48 hours. High: within 1 week. Medium/low: next release. |
| Third-party tracking | Maintain a dependency inventory (`gradle/libs.versions.toml`). Automated scanning flags outdated or vulnerable libraries. |
| Incident response | Documented process for side-loaded APK recall and hotfix distribution. Single point of contact designated. |

---

## 5. Deferred Controls (Rationale)

| Control | NFR Source | Reason for Deferral |
|---------|------------|---------------------|
| TLS / network encryption | NFR-010 | No network communication in MVP — zero attack surface. Apply when backend sync is introduced. |
| Certificate pinning | NFR-013 | No network in MVP. Apply when API communication is added. |
| CSRF protection | NFR-013 | Web-only threat. N/A for Android native app with no web views in MVP. |
| Security headers | NFR-013 | Web-only. N/A for MVP. |
| Server-side encryption | NFR-010 | No server in MVP. Apply when Spring Boot backend is introduced. |
| OAuth2 / Keycloak | NFR-009 | No multi-user or backend in MVP. Apply when authentication is needed. |
| Biometric / app-lock | NFR-013 | Deferred by product decision. The device lock screen is the sole authentication gate in MVP. |

---

## 6. Implementation-Action Items

The following items require code changes beyond this policy document. They will be created as backlog tasks with dependency on TASK-0005.

| # | Action | Priority | Notes |
|---|--------|----------|-------|
| 1 | Add SQLCipher and AndroidX Security Crypto dependencies | High | `net.zetetic:android-database-sqlcipher`, `androidx.security:security-crypto` |
| 2 | Create `MasterKey` singleton with Keystore-backed AES-256 config | High | Shared single instance across the app |
| 3 | Migrate Room database to use `SupportFactory` with the MasterKey | High | Affects `core-database` module |
| 4 | Replace all plain `SharedPreferences` with `EncryptedSharedPreferences` | High | Audit all modules for usage |
| 5 | Implement `EncryptedFile` for photo / document attachments | High | Storage in internal app directory |
| 6 | Enable ProGuard/R8 for release builds and write keep rules | High | Currently `isMinifyEnabled = false` in `app/build.gradle.kts` |
| 7 | Add input validation to all user-facing forms | Medium | Per-screen audit |
| 8 | Add debug-build detection and runtime guard | Medium | Terminate on release build with debug flags |
| 9 | Set up SAST (Detekt) + dependency scanning in CI | Medium | Depends on CI pipeline existence |
| 10 | Add `SecureRandom` usage for all generated identifiers | Low | Replace any `Random()` usage |

---

## 7. Review Cycle

This policy is reviewed:
- When a new feature introduces a network layer (backend sync, cloud backup, etc.)
- When the app is prepared for Play Store distribution
- At minimum every 12 months
