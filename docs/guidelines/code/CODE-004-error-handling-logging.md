# CODE-004 Error Handling & Logging

**Applies to:** mobile, backend, web

## Guideline

Errors must be caught at layer boundaries, logged appropriately, and surfaced to users in a meaningful way. Never expose internal details to end users.

### Error Taxonomy
- **Domain errors**: handled at the domain layer (business rule violations, invalid state transitions).
- **Application errors**: handled at the use-case / service layer (not found, conflict, unauthorized).
- **Infrastructure errors**: handled at the adapter layer (database connection failure, network timeout).
- **Unexpected errors**: caught by a global error handler, logged, and a generic message returned to the user.

### Logging
- Backend: structured JSON logging. Events include `timestamp`, `level`, `service`, `traceId`, `userId`, `message`.
- Mobile: use Android `Log` class or a lightweight logger. No PII or encryption material in logs (see SEC-006).
- Log levels: `ERROR` (failures requiring investigation), `WARN` (unexpected but handled), `INFO` (state changes, lifecycle), `DEBUG` (development only — stripped in release builds).
- Do not log stack traces for handled/expected errors — log the business context instead.

### User-Facing Errors
- Show human-readable messages. Never expose stack traces, SQL queries, or internal identifiers.
- Provide actionable guidance (e.g. "Connection lost. Check your network and try again." not "java.net.ConnectException: Connection refused").
