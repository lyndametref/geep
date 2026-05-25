# SEC-008 BSA Compliance (Build Security Assurance)

**Applies to:** mobile, backend, web

## Guideline

Development and deployment practices must align with BSA (Build Security Assurance) secure development standards.

- Security awareness: all contributors must read security policies. Threat modeling orientation included in onboarding.
- Vulnerability SLA: critical ≤ 48 hours, high ≤ 1 week, medium/low → next release.
- Third-party component tracking: maintain a dependency inventory (`gradle/libs.versions.toml`). Automated scanning flags outdated or vulnerable libraries.
- Incident response: documented process for side-loaded APK recall and hotfix distribution. Designated security point of contact.

## Rationale

BSA establishes baseline security practices for software development, ensuring consistent security posture across the supply chain.
