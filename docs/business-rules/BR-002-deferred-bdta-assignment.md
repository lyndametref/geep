# BR-002: Deferred BDTA Assignment

## Description

National ear tag identifier association may be omitted initially and assigned later. This attribute is thus optional

## Rationale

Based on requirement REQ-01.003 — Manual identifier management, this rule allows shepherds to create an individual record during field operations (e.g., at birth) without having the official ear tag number at hand. The BDTA can be recorded in a subsequent update, reducing data entry friction at the point of capture.

## Applicability

Applies at individual creation time and during subsequent updates. The earTagId field is nullable and can be set or modified at any point in the individual's lifecycle.
