# NFR-04.006 OWASP Compliance

## Description

Security controls must address the OWASP Top 10 vulnerabilities. Input validation and output encoding must be applied consistently. No hardcoded credentials may exist in the codebase. SQL injection prevention must use parameterized queries. CSRF protection must be implemented for state-changing operations. Security headers (CSP, X-Frame-Options, etc.) must be configured.

## Rationale

OWASP Top 10 represents the most critical web application security risks. Systematic mitigation prevents common attack vectors that could compromise livestock data, user accounts, or system integrity. Addresses the security and compliance quality attributes.

## Verification Method

Verified by running automated SAST scanning for OWASP Top 10 vulnerabilities, reviewing code for parameterized query usage, confirming CSRF tokens on state-changing endpoints, and auditing HTTP response headers for security header presence. Credential scanning must confirm no hardcoded secrets.
