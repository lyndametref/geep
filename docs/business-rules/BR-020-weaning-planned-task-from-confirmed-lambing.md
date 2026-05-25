# BR-020: Weaning Planned Task from Confirmed Lambing

## Description

A confirmed lambing derives a weaning Planned Task for each created lamb Individual. The delay to weaning can be configured by the flock manager

## Rationale

Based on requirement REQ-04.005 — A confirmed lambing proposes creation of lamb records as well as a weaning event after a configurable interval, this rule automates scheduling a weaning reminder after a configurable delay (defaulting to the typical weaning age of 3 months). The Planned Task ensures the shepherd does not miss the weaning window. The task is derived only after the lamb Individual record is created.

## Applicability

Applies when a lamb Individual record is created from a confirmed lambing. The weaning date is calculated as birthDate + configured weaning age.
