# BR-018: Record Ownership

## Description

A record is associated to exactly one Individual. This association is immutable.

## Rationale

Based on requirements [REQ-04](../requirements/business/REQ-04.md) — Observations and Reproductive Planning, and [REQ-13](../requirements/business/REQ-13.md) — Interventions and Care Management, this rule ensures each journal entry is attributed to a specific Individual. Even when recording a batch event, the system creates one record per Individual, preserving clear ownership and accountability for every observation and intervention.

## Applicability

Applies to all Record creation. The record association to an individual is mandatory and immutable.