# NFR-04.005 SSDF Compliance

## Description

Development practices must align with NIST SSDF (Secure Software Development Framework) guidance. Build automation must prevent manual package deployment. All code changes must be subject to peer review before merge. Automated security scanning (SAST, dependency checks) must be integrated into the CI/CD pipeline. Releases must be cryptographically signed.

## Rationale

SSDF compliance ensures security is built into the software development lifecycle, reducing the risk of vulnerabilities reaching production. Addresses the security and compliance quality attributes.

## Verification Method

Verified by auditing the CI/CD pipeline to confirm build automation, mandatory peer review gates, SAST scanning, and dependency vulnerability checks. Cryptographic signing of releases must be verified by inspecting release artifacts for valid signatures.
