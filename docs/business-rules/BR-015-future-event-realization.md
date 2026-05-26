# BR-015: Future Event Realization

## Description

When a Future Event materializes, a new Record is created linked to the source Future Event. The type of Record depends on the subtype:
- A Predicted Event realization creates an Observation.
- A Planned Task realization creates an Intervention.
- A Waiting Delay elapse creates no Record.

## Rationale

Based on requirement [REQ-04](../requirements/business/REQ-04.md) — Observations and Reproductive Planning, this rule closes the loop between planning and recording. Rather than mutating the Future Event's state in isolation, realization is recorded as the appropriate Record type that references the original Future Event as its source. This preserves an auditable trail: the prediction, its realization, and any follow-up actions remain linked in the individual's journal. Waiting Delays represent elapsed time periods rather than events, so no record is needed.

## Applicability

Applies when a user confirms that a Predicted Event or Planned Task has been realized. The new Observation or Intervention includes a reference to the source Future Event's record ID.
