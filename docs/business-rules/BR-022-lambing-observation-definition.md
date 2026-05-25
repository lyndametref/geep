# BR-022: Lambing Observation Definition

## Description

A lambing observation records a lambing event for a single ewe and captures the lambing date and number of lambs born.

## Rationale

Based on requirements REQ-04.001 — Observation types include weight evolution, health observations, and reproduction events, and REQ-04.005 — A confirmed lambing proposes creation of lamb records as well as a weaning event at a configurable interval. Each lambing observation is associated with exactly one dam in accordance with BR-021. The number of lambs enables the system to propose the correct number of lamb Individual records per BR-013.

## Applicability

Applies when recording a reproduction observation of type lambing. The number of lambs is a positive integer. The lambing date is recorded as the observation date.
