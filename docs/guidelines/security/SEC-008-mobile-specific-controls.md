# SEC-008 Mobile-Specific Security Controls

**Applies to:** mobile

## Guideline

The Android application must enforce platform-specific security controls for local-only offline operation.

- No dependency on Google Play Services.
- Android Keystore MasterKey: `MasterKey.Builder` with `setKeyScheme(AES256_GCM)` and `setUnlockedDeviceRequired(true)`.
- Room database: use `SupportFactory` with SQLCipher for encrypted storage.
- File attachments: stored in internal app directory using `EncryptedFile`.
- Debug-build guard: terminate release builds when debug flags are detected.
- Log sanitization: strip PII and encryption material from release-build logs.
- SecureRandom: use `java.security.SecureRandom` for all generated IDs (UUIDs, filenames).
- No analytics, crash reporting, or telemetry in MVP.
- Biometric / app-lock: deferred by product decision — device lock screen is the sole auth gate.

## Rationale

Mobile devices operate in uncontrolled environments (field work) and are susceptible to loss or theft. Platform-specific controls ensure data remains protected even when the device is compromised.
