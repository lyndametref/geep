# BR-011: Observation Scope

## Description

Observation entries can be associated with one or many individuals.

## Rationale

Based on requirement REQ-04 — Observations and Reproductive Planning, this rule enables recording events that affect multiple individuals simultaneously (e.g., a health observation of an entire grazing group, or a weather event affecting a batch). The UI batch entry workflow (BR-006) creates individual Record instances per individual, but the observation content may logically describe a group event.

## Applicability

Applies to observation creation. UI may present a batch selection interface; persistence layer creates one Observation per individual with shared or identical content.
