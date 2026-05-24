# Context Map

This model captures the shared business meaning of individual lifecycle, genealogy, observations, care, derived planning, and the REQ-03.001, REQ-03.002, and REQ-03.003 placeholder for Geep, without implementation or persistence details.

```mermaid
graph LR
    IndividualMgmt["Individual Management"] -->|includes refs| Genealogy["Genealogy Graph"]
    IndividualMgmt -->|excludes refs| Journal["Journaling"]
    Journal --> Planning["Planning"]
    IndividualMgmt -->|excludes refs| TraitAssessment["Trait Assessment Placeholder"]
    Genealogy --> TraitAssessment
```

## Context Descriptions

### Individual Management
Manages the lifecycle and identity of individuals, including registration, status, flock membership, and parentage links used by downstream contexts. Individual Management owns the `belongsToFlock` flag that downstream contexts must respect for filtering lineage individuals.

### Genealogy Graph
Represents lineage relationships between individuals and supports ancestry-based reasoning and navigation. Includes lineage individuals (belongsToFlock = false) in the graph.

### Journaling
Captures observations, interventions, and related evidence as chronological records associated with one or more individuals. Excludes lineage individuals from day-to-day journal operations.

### Planning
Holds future-oriented items derived from journal activity, including predictions, planned actions, and waiting delays.

### Trait Assessment Placeholder
Reserves the domain area for phenotype capture and uncertain genotype representation until detailed deduction rules are defined.