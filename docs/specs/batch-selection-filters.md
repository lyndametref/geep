# Batch selection filters

## Scope

This spec covers individual selection for batch observation entry ([REQ-04.003](../requirements/business/REQ-04.003.md)) and batch intervention entry ([REQ-13.003](../requirements/business/REQ-13.003.md)). The selection UI is shared between both flows.

## Exclusions

The following individuals are excluded from batch selection results:

- **Lineage individuals** — those with `belongsToFlock = false` ([REQ-01.008](../requirements/business/REQ-01.008.md))
- **Stillborn individuals** — those with `stillborn = true` ([BR-004](../business-rules/BR-004-stillborn-lifecycle-progression.md))

## Filters

All filters are optional, can be combined, and are evaluated with logical AND. When no filters are active, all non-excluded flock individuals are shown.

### Text search

A free-text field that matches against `name` or `officialId` (partial, case-insensitive). Shows individuals where either field contains the search term.

### Sex filter

An enum selection: `Male`, `Female`. Only individuals whose `sex` matches the selection are shown.

### Age filter

Two modes are available:

**By age (years):** A min/max range with inclusive bounds. Age is calculated as:

```
age = years between birthDate and currentDate, floored to full years
```

Examples:
- An individual born 2024-06-15 on date 2026-05-25 → age = 1
- An individual born 2024-01-01 on date 2026-05-25 → age = 2

Shows individuals whose age is within the provided range.

**By year of birth:** A min/max range of birth years (e.g. 2023–2024). Shows individuals whose `birthDate` year falls within the provided range.

### Manual selection

After applying filters, the user can manually select or deselect individuals from the resulting list via checkboxes.

## Flow

1. User start record  (observation or intervention) creation
2. Selection UI presents the filter controls and a scrollable individual list
3. User applies optional filters; the list updates automatically
4. User selects individuals manually via checkboxes
5. User confirms selection and proceeds to data entry
