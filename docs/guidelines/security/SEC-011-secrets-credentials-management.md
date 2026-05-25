# SEC-011 Secrets & Credentials Management

**Applies to:** mobile, backend, web

## Guideline

No secrets, API keys, tokens, passwords, or cryptographic material may appear in source code. All secrets must be managed through secure infrastructure.

## Rationale

Hardcoded secrets are the leading cause of credential exposure. Centralized secret management ensures rotation, audit, and access control.
