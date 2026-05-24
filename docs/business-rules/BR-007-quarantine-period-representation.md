# BR-007: Quarantine Period Representation

## Description

Treatment quarantine is modeled as two independent optional periods: one for meat withdrawal and one for milk withdrawal.

## Rationale

Based on requirement REQ-13.004 — Quarantine management (meat and milk withdrawal periods after treatments), this rule reflects the regulatory reality that a single treatment may impose different waiting periods before the animal's meat versus milk can be used. Modeling them as two independent WaitingDelay instances allows each to have its own elapsed date and status without conflating two distinct timelines.

## Applicability

Applies when creating a treatment intervention that requires quarantine tracking. Zero, one, or two WaitingDelay entries may be derived depending on the treatment product's withdrawal specifications.
