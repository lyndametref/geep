# DEPLOY-002 Containerization Standards

**Applies to:** backend, web

## Guideline

All backend and web services must be containerized using Podman (or Docker) with best-practice image patterns.

## Rules

- Base images: use minimal, distroless, or Alpine-based official images. No full OS images.
- Multi-stage builds: separate build stage from runtime stage to minimize image size and attack surface.
- No hardcoded configuration or secrets in container images. All config via environment variables.
- Images must be tagged with immutable version identifiers (semver or commit SHA), never `latest`.
- Run as non-root user. Set `USER` directive in the Dockerfile.
- Set `HEALTHCHECK` instruction for each service.
- Container images must be scanned for vulnerabilities before deployment.
- Use `.dockerignore` to exclude build artifacts, tests, and dev dependencies from the image context.
 