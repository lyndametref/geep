# WaitingDelay — State Diagram

WaitingDelay is a specialization of FutureEvent representing a delay interval that must elapse before normal operations resume (e.g., a quarantine withdrawal period after treatment). It uses the `DelayStatus` enum with three lifecycle states.

## States

| State | Description |
|-------|-------------|
| `Waiting` | Delay period in progress — the waiting interval is active. Initial state upon derivation from a treatment intervention. |
| `Elapsed` | The delay period has ended — the current time has passed `delayElapsedAt`. Terminal state. |
| `Aborted` | The delay was cancelled before elapsing (e.g., the treatment was invalidated). Terminal state. |

## Transitions

| From | To | Trigger | Conditions |
|------|----|---------|------------|
| `[*]` | `Waiting` | WaitingDelay is derived | A treatment intervention with withdrawal period specifications is recorded. BR-014 calculates `delayElapsedAt` from treatment date + product-specific withdrawal duration. One or two WaitingDelay entries are created per BR-007 (meat withdrawal, milk withdrawal, or both). |
| `Waiting` | `Elapsed` | System detects that current time >= `delayElapsedAt` | The delay period naturally ends when the current time reaches the calculated `delayElapsedAt`. Optionally, an Observation may be recorded referencing this WaitingDelay via `sourceFutureEventId` to confirm the delay completion. [BR-014, BR-015] |
| `Waiting` | `Aborted` | User or system aborts the delay | The delay is cancelled (e.g., the treatment is invalidated, or the withdrawal period is overridden). [REQ-13.004] |

## Diagram

```mermaid
stateDiagram-v2
    [*] --> Waiting : derived / treatment recorded with withdrawal period (BR-014)
    Waiting --> Elapsed : elapsed / current time passes delayElapsedAt (BR-014, BR-015)
    Waiting --> Aborted : aborted / delay cancelled (REQ-13.004)
    Elapsed --> [*]
    Aborted --> [*]
```
