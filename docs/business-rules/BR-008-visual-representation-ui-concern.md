# BR-008: Visual Representation as UI Concern

## Description

Individual visual representation (photo, avatar, icon) is a UI concern and is not represented as a domain entity in this model.

## Rationale

Based on requirement REQ-01 — Individual Management, this rule scopes the domain model to data that drives business logic and reporting. Visual assets (photos, avatars) are presentation-layer concerns attached to records as Attachments, not attributes of the Individual entity itself. This keeps the domain focused on identity, lifecycle, and lineage rather than rendering.

## Applicability

Applies to individual representation in UI. Photos and other visual media are stored as Attachment records linked to an observation or intervention, not as direct Individual attributes.
