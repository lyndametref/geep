# DEPLOY-007 Monitoring & Observability

**Applies to:** backend, web

## Guideline

Production systems must be observable through structured logging, metrics export, and distributed tracing.

## Rules

### Logging
- Structured JSON logging for all backend services.
- Each log entry must include: `timestamp`, `level`, `service`, `traceId`, `userId` (if authenticated), `message`.
- Log aggregation: central log management system.

### Metrics
- Export standard metrics: CPU, memory, disk, network I/O, database query latency, HTTP request rate/duration/errors.
- Expose metrics and visualise in dashboard.

### Tracing
- Distributed tracing for request flow visibility across services
- Trace every API request end-to-end: web → backend → database.

### Health Checks
- Backend:  readiness, liveness, and startup probes.
- Regular Automated health checks.
