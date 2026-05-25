# BR-009: Record Unique Identifier

## Description

Every Record (Observation and Intervention) and Attachment has a mandatory unique internal identifier.

## Rationale

Based on requirements REQ-04 — Observations and Reproductive Planning, and REQ-13 — Interventions and Care Management, this rule ensures that each journal entry and its attachments can be reliably referenced, associated with an individual, and linked to source FutureEvents regardless of the data the user provides. The identifier is immutable and system-generated to guarantee uniqueness across all Record subtypes and Attachments.

## Applicability

Applies at Record and Attachment creation time. The identifier is assigned once and never modified.
