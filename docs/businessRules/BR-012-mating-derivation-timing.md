# BR-012: Mating Derivation Timing

## Description

A mating observation derives a birth PredictedEvent with a 140 to 150 day window after the observation date.

## Rationale

Based on requirement REQ-04.004 — Reproductive planning events (predicted birth windows derived from mating observations), this rule encodes the typical sheep gestation period. The 140-150 day range reflects the natural variability in gestation length, providing a realistic predicted birth window rather than a single date. The PredictedEvent uses earliestDate = observation date + 140 days and latestDate = observation date + 150 days.

## Applicability

Applies when an observation of type mating is recorded. A PredictedEvent is automatically derived. The window is calculated from the observation's observedAt timestamp.
