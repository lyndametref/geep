# REQ-02.001 The genealogy view displays individuals as nodes of a graph

## User Story
As a shepherd, I want ancestry and parentage information to be displayed as a graph so that I can understand lineage and inherited characteristics at a glance.

## Group
REQ-02 Genealogy and Parentage Graph

## Criticality
Must have

## Description
The genealogy view must display genealogy data as a graph where individuals are nodes and parentage relationships are edges. This graph representation is a presentation concern — the underlying data may be stored in any suitable structure (relational tables, document store, etc.).

## Acceptance Criteria
- The genealogy view renders individuals as nodes.
- The genealogy view renders parentage links as edges between individuals.
