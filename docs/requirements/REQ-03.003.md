# REQ-03.003: The system proposes possible genotypes for an individual based on available lineage and phenotype information.

As a sheperd, I want the system to propose possible genotypes for an individual so that I can reason about inherited traits with decision support.

Group: REQ-03 Phenotype and Genotype Deduction
Criticality Must have

## Description
The system must propose possible genotypes for an individual based on available ancestry genotype information, the individual's phenotype, and the phenotype of descendants.

## Acceptance Criteria
- The system can generate genotype propositions for an individual.
- Genotype propositions take into account available ancestry genotype information.
- Genotype propositions take into account the individual's phenotype.
- Genotype propositions take into account available descendant phenotype information.
