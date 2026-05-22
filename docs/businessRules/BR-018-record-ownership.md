# BR-018: Record Ownership

## Description

A record belongs to exactly one individual.

## Rationale

Based on requirements REQ-04 — Observations and Reproductive Planning, and REQ-13 — Interventions and Care Management, this rule ensures each journal entry is attributed to a specific individual. Even when recording a batch event (see BR-006), the system creates one record per individual, preserving clear ownership and accountability for every observation and intervention.

## Applicability

Applies to all Record creation. The individualId foreign key is mandatory and immutable once set.
