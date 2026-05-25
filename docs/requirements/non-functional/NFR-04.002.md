# NFR-04.002 Authentication and Identity Management

## Description

All user access must authenticate via OAuth2 or OpenID Connect using Keycloak as the identity provider. Authentication must redirect users to Keycloak. JWT tokens must be issued and validated for API requests. Session timeout must be enforced with a default of 30 minutes of inactivity. Multi-factor authentication (MFA) must be supported.

## Rationale

Centralized identity management ensures consistent authentication policies, supports MFA for elevated security, and enables integration with enterprise identity providers. Addresses the security, access control, and compliance quality attributes.

## Verification Method

Verified by performing authentication flows through Keycloak and confirming successful JWT token issuance and API-level validation. Session timeout must be tested by allowing 30 minutes of inactivity and confirming forced re-authentication. MFA must be tested by enrolling a test user with a second factor.
