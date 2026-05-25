# BR-005: Parentage Role Semantics

## Description

Parentage is represented as child-to-parent relationships between Individuals.
A sire must be a male Individual; a dam must be a female Individual.
Zero or more sire (male parent), and zero or one dam (female parent) can be provided.

## Rationale

Based on requirements REQ-01.005 — Sex, color pattern, and lineage information, and REQ-02 — Genealogy and Parentage Graph, this rule defines parentage as a relationship from child to sire and dam. 

Sex-typed roles (sire = male, dam = female) provide clarity in genealogy views. 

Multiple represent mating group with multiple rams where the parentage is uncertain. No dam represent a lamb found without knowing from which ewe or purchased individual without lineage knowledge.

## Applicability

Applies at individual creation and when modifying parentage links. 