---
id: GEEP-0012
title: M1 Genealogy View UI
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, ui, feature-genealogy]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005, GEEP-0007]
ordinal: 12
---

## Description

Implement the Genealogy view UI in the `:feature:genealogy` module. Displays individuals as graph nodes with icons and display labels, with directed parentage edges (sire/dam role differentiation). Supports REQ-02.001-02.003 and BR-023.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Genealogy view renders individuals as graph nodes with a generated icon (procedural based on sex and color pattern) and display label (name > officialId > #id fallback) — covers REQ-02.001 and REQ-02.002
- [ ] #2 Parentage relationships are rendered as directed edges between nodes with sire/dam role differentiation (different colors or labels) — covers REQ-02.003
- [ ] #3 User can tap a node to view individual details or navigate to the individual detail screen
- [ ] #4 View supports basic graph layout (spring or layered) that avoids node overlap and renders up to 3 generations
- [ ] #5 Lineage individuals are included in genealogy view (they are excluded only from day-to-day flock management) — covers REQ-01.008
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Feature module compiles
- [ ] #2 Genealogy graph renders correctly with test data
- [ ] #3 Node tap navigation works
<!-- DOD:END -->
