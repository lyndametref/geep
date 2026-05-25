# NFR-04.003 Data Encryption

## Description

Sensitive data must be encrypted both in transit and at rest. TLS 1.2 or higher is required for all network communication. Database encryption must be enabled at the storage layer for both PostgreSQL and Neo4j. Object storage must use server-side encryption (SSE-S3 or KMS). API keys and secrets must be stored in encrypted vaults.

## Rationale

Encryption protects sensitive livestock, health, and personal data from unauthorized access both during transmission and at rest. Required for regulatory compliance and to maintain stakeholder trust. Addresses the confidentiality, integrity, and compliance quality attributes.

## Verification Method

Verified by performing network traffic analysis to confirm TLS 1.2+ is used for all external communication. Database storage layer encryption must be confirmed via configuration audit. Secrets management must be inspected to confirm keys and secrets are stored in encrypted vaults (e.g., HashiCorp Vault, Kubernetes Secrets with encryption).
