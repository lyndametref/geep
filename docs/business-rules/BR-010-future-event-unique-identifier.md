# BR-010: Future Event Unique Identifier

## Description

Every FutureEvent (PredictedEvent, PlannedTask, and WaitingDelay) has a mandatory unique internal identifier.

## Rationale

Based on requirements REQ-04 — Observations and Reproductive Planning, and REQ-13 — Interventions and Care Management, this rule ensures that each future event can be reliably referenced, associated with an individual, and linked to source Records regardless of the data the user provides. The identifier is immutable and system-generated to guarantee uniqueness across all FutureEvent subtypes.

## Applicability

Applies at FutureEvent creation time. The identifier is assigned once and never modified.
