# BR-017: Trait Assessment Scope

## Description

Trait (phenotype) assessment capture is explicit in the model while deduction algorithms (genotype inference) remain out of scope for the MVP.

## Rationale

Based on requirement REQ-03 — Phenotype and Genotype Deduction, this rule scopes the MVP to recording observable traits (color pattern, weight, body condition) without implementing the inference engine that would deduce possible genotypes from phenotype data. The data model supports phenotype capture so that deduction can be added as a future capability without structural changes.

## Applicability

Applies to trait recording on individuals. Color pattern, body condition score, and other observable traits may be recorded. No automated genotype deduction is performed in the MVP.
