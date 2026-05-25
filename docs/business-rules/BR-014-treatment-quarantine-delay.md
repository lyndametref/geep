# BR-014: Treatment Quarantine Delay

## Description

A treatment intervention can derive quarantine Waiting Delay entries that elapse at calculated dates based on the product's withdrawal periods.

## Rationale

Based on requirement REQ-13.004 — Quarantine management (meat and milk withdrawal periods after treatments), this rule connects treatment recording to quarantine tracking. When a treatment with known withdrawal periods is administered, the system derives Waiting Delay entries — one per withdrawal period (meat, milk, or both as per BR-007). Each delay is calculated from the treatment date plus the product-specific withdrawal duration.

## Applicability

Applies when creating a treatment intervention that has associated withdrawal period specifications. Zero, one, or two Waiting Delay entries are derived depending on the treatment product.
