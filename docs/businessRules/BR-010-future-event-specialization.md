# BR-010: Future Event Specialization

## Description

PredictedEvent, PlannedTask, and WaitingDelay are specialized forms of FutureEvent.

## Rationale

Based on requirement REQ-04 — Observations and Reproductive Planning, this rule recognizes three distinct kinds of future-oriented entries: predicted probabilistic events (e.g., expected birth window from mating), planned concrete tasks (e.g., weaning reminder), and elapsed-period delays (e.g., quarantine waiting period). A shared FutureEvent supertype allows unified lifecycle management (pending, realized, aborted) while each subtype defines its own status model and date semantics.

## Applicability

Applies when a future event is derived from an observation or intervention. The subtype is determined by the nature of the derivation: observations of type mating produce PredictedEvent, confirmed births produce PlannedTask, treatments may produce WaitingDelay.
