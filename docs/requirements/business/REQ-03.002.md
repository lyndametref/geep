# REQ-03.002 The sheep owner can enter genotype information.

## User Story
As a shepherd, I want to enter genotype information even when I am not fully certain so that I can keep partial knowledge and refine it later.

## Group
[REQ-03 Phenotype and Genotype Deduction](REQ-03.md)

## Criticality
Should have

## Description
The system must allow the sheep owner to enter genotype information for an individual. The possible genotype entries that can be recorded should be configurable by the flock manager. When the genotype is not yet certain, it must be possible to mark it as unconfirmed. In this state, multiple alleles can be provided for a given gene.

## Acceptance Criteria
- A sheep owner can enter genotype information for an individual from a list of options that can be configured by the flock manager.
- A genotype can be marked as unconfirmed.
- When a genotype is unconfirmed, multiple alleles can be provided for a given gene.
