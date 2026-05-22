# BR-013: Birth Follow-Up Derivation

## Description

A confirmed birth derives a weaning PlannedTask approximately 3 months later and supports a lamb-record creation proposal to the user.

## Rationale

Based on requirement REQ-04.005 — Birth follow-up (weaning reminders and lamb record creation), this rule automates two post-birth actions: scheduling a weaning reminder 3 months after birth (typical weaning age for lambs), and prompting the user to create a formal individual record for the newborn. The PlannedTask ensures the shepherd does not miss the weaning window. The lamb-record proposal is a suggestion, not an automatic creation — the user decides whether and when to record the new individual.

## Applicability

Applies when a birth observation is recorded or when a birth PredictedEvent is confirmed as realized. The weaning date is calculated as birthDate + 3 months. The lamb-record proposal appears in the UI as an actionable suggestion.
