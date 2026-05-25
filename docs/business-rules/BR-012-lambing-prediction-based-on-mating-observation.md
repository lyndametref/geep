# BR-012: Lambing prediction based on mating observation

## Description

A mating observation derives a Predicted Event with a window between 140 and 150 days after the observation date.

## Rationale

Based on requirement REQ-04.004 — Reproductive planning events (predicted birth windows derived from mating observations), this rule encodes the typical sheep gestation period. The 140-150 day range reflects the natural variability in gestation length, providing a realistic predicted birth window rather than a single date. 

## Applicability

Applies when an observation of type mating is recorded. A Predicted Event is automatically derived. The window is calculated from the observation timestamp not the record timestamp. The Predicted Event has an earliest date = observation date + 140 days and a latest date = observation date + 150 days.

