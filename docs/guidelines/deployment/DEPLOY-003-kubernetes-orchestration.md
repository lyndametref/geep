# DEPLOY-003 Kubernetes Orchestration & Scaling

**Applies to:** backend

## Guideline

Backend services must use Kubernetes for orchestration with horizontal scaling, health checks, and graceful shutdown.

- Backend services must be stateless to enable horizontal scaling.
- Readiness probes and liveness probes must be configured for all pods.
- Pod Disruption Budgets (PDBs) must be set for production deployments.
- Resource requests and limits must be specified for every container.
- Use startup probes for services with slow initialization (e.g., database connection warmup).
- Graceful shutdown: handle SIGTERM, drain connections, complete in-flight requests before exiting.
