# BR-009: Journal Entry Specialization

## Description

Observation, Intervention, and MedicalAnalysisResult are specialized forms of journal entries sharing a common Record supertype.

## Rationale

Based on requirements REQ-04 — Observations and Reproductive Planning, and REQ-13 — Interventions and Care Management, this rule establishes that observations (health, weight, reproduction events), interventions (treatments, shearing, hoof trimming), and medical analysis results all share common journaling properties (timestamp, individual association, attachments) while each carrying type-specific data. A polymorphic Record supertype enables unified journal queries across all entry types.

## Applicability

Applies to all journal entry creation. Each entry type is stored as a specialization of Record with its own subtype-specific attributes and validation rules.
