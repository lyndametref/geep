# DEPLOY-001 Multi-Environment Deployment Strategy

**Applies to:** backend, web

## Guideline

The same codebase must deploy to local (Podman), public cloud, and private cloud environments without core functionality degradation.

- One codebase for all environments. No environment-specific code branches or build artifacts.
- All environment-specific configuration must be externalized via environment variables or ConfigMaps.
- Build once, deploy many: produce a single artifact per release that runs in any environment.
- Feature flags may be used to enable/disable environment-specific capabilities (e.g. cloud backup).
- Each environment must have its own configuration profile: `local`, `staging`, `production`.
- CI/CD pipeline must be environment-aware: promote artifacts through `dev → staging → production`.
