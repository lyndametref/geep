# BR-023: Genealogy Graph Structure and Edge Display

## Description

Genealogy views display parent-child relationships as a directed graph where each Individual is a node and each parent-child relationship is a directed edge with sire/dam role differentiation. Views display parentage links as directed edges between individual nodes. The graph representation is a presentation concern — the underlying data may be stored in any suitable structure. 

- Views support traversal for lineage queries:
  - Finding **ancestors** of an individual: follows parent edges (sire/dam) recursively upward — includes all direct forebears (parents, grandparents, etc.). A **level** can be specified to indicate generational distance (parents = level 1, grandparents = level 2, etc.)
  - Finding **descendants** of an individual: follows incoming parent edges recursively downward — includes all direct offspring (children, grandchildren, etc.). A **level** can be specified to indicate generational distance (children = level 1, grandchildren = level 2, etc.)
  - Finding **siblings** of an individual: finds all individuals that share at least one parent (sire or dam) with the target individual
  - Finding **parents** of an individual: returns the direct sire (father) and dam (mother) of the target individual
- 

## Rationale

Based on requirements [REQ-02.001](../requirements/business/REQ-02.001.md) — The genealogy view displays individuals as nodes of a graph, and [REQ-02.003](../requirements/business/REQ-02.003.md) — The genealogy view displays parentage links as edges. This rule exists because the system must render parentage relationships as a navigable graph in genealogy views to make lineage understandable. [BR-005](BR-005-parentage-role-semantics.md) defines the role semantics of parent-child relationships (sire/dam); this rule complements it by defining how those relationships are visually represented as a directed graph.

## Applicability

Applies to all genealogy view rendering where parent-child relationships are displayed as visual edges between individual nodes.
