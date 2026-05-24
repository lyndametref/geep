# BR-015: Future Event Realization

## Description

When a FutureEvent materializes (e.g., a predicted birth occurs, a planned task is completed), realization is captured by creating a new Observation entry linked to the source FutureEvent.

## Rationale

Based on requirement REQ-04 — Observations and Reproductive Planning, this rule closes the loop between planning and recording. Rather than mutating the FutureEvent's state in isolation, realization is recorded as an Observation that references the original FutureEvent as its source. This preserves an auditable trail: the prediction, its realization, and any follow-up actions remain linked in the individual's journal.

## Applicability

Applies when a user confirms that a PredictedEvent, PlannedTask, or WaitingDelay has occurred or been completed. The new Observation includes a reference to the source FutureEvent's record ID.
