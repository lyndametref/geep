# BR-002: Deferred Official Identifier Assignment

## Description

Official Identifier association may be omitted at the Individual creation  and assigned later.

## Rationale

Based on requirement REQ-01.003 — For individual entries without an assigned BDTA number yet, the system allows later BDTA assignment, this rule allows shepherds to create an individual record during field operations (e.g., at birth) without having the Official Identifier at hand. The Official Identifier can be recorded in a subsequent update, reducing data entry friction at the point of capture.

## Applicability

Applies at individual creation time and during subsequent updates. The Official Identifier attribute is nullable and can be set or modified at any point in the individual's lifecycle.
