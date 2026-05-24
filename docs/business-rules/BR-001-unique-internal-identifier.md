# BR-001: Unique Internal Identifier

## Description

Every Individual has a mandatory unique internal identifier.

## Rationale

Based on requirement REQ-01.001 — Each individual must have a unique identifier, this rule ensures that each sheep record can be reliably referenced and associated with related data (observations, interventions, lineage links) regardless of whether the user provides a separate manual identifier (such as an ear tag identifier). The identifier is immutable and system-generated to guarantee uniqueness.

## Applicability

Applies at individual creation time. The identifier is assigned once and never modified.
