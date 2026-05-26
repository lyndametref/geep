---
id: GEEP-0007
title: M1 Genealogy Traversal Service
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, genealogy]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005]
ordinal: 7
---

## Description

Implement a local graph traversal service in the `:core:rules` module for genealogy queries. This service queries the database via repositories and performs in-memory graph traversal to find ancestors, descendants, siblings, and parentage edges for the genealogy view. Covers BR-023 genealogy graph structure.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 GetAncestors(individualId, maxLevels) returns all ancestors up to specified generation depth, ordered by proximity (parents = level 1, grandparents = level 2)
- [ ] #2 GetDescendants(individualId, maxLevels) returns all descendants up to specified generation depth
- [ ] #3 GetSiblings(individualId) returns all individuals sharing at least one parent (sire or dam) with the target
- [ ] #4 GetParentageEdges(individualIds) returns a list of directed parent-child edges for a given set of individuals, with sire/dam role differentiation
- [ ] #5 DisplayLabelResolver computes the display label per REQ-02.002 priority: name > officialId > generated fallback (#id)
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Genealogy service compiles
- [ ] #2 Unit tests verify ancestor/descendant/sibling queries against a populated in-memory graph
- [ ] #3 Display label resolution is tested for all priority scenarios
<!-- DOD:END -->
