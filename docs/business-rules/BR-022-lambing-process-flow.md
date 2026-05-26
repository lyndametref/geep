# BR-022: Lambing Process Flow

## Description

- Only a female Individual (ewe) can have lambing Observation recorded.
- A lambing observation records a lambing event for a single ewe and captures the lambing date and number of lambs born.
- After saving a lambing Observation the creation of Individual record, one per newborn. The user can create, defer, or dismiss the proposal. No more lambs than the count specified in the lambing observation can be created through this process.
- After a newborn lamb individual lamb creation, a weaning Planned Task for each created lamb Individual. Per default a configurable delay after birth is proposed, but the sheperd can enter any other date. The standard delay to weaning can be configured by the flock manager.

## Rationale

Based on requirements [REQ-04.001](../requirements/business/REQ-04.001.md) — Observation types include weight evolution, health observations, and reproduction events, and [REQ-04.005](../requirements/business/REQ-04.005.md) — A confirmed lambing proposes creation of lamb records as well as a weaning event at a configurable interval. This rule exists because lambing is a multi-step process: the observation captures the event, the shepherd is prompted to register each newborn as an Individual, and a weaning reminder is automatically scheduled. The sex constraint ensures only ewes can be lambing subjects. The weaning delay is configurable to accommodate different management practices.

## Applicability

Applies when recording a lambing observation. The Individual referenced as the lambing subject must be female. A lamb-record creation proposal is presented for each newborn. A weaning Planned Task is derived for each created lamb Individual, calculated as birthDate + configured weaning age.
