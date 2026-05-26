# REQ-01.008 Individual flock membership

## User Story
As a shepherd, I want to know which individuals are currently in my flock and track when and how they entered or left so that I can manage day-to-day operations accurately and maintain a complete entry and departure history.

## Group
[REQ-01 Individual Management](REQ-01.md)

## Criticality
Must have

## Description

Every Individual in the system is either currently part of the Flock Manager's active flock or not. The system tracks when and how an individual joins the flock and, for individuals that have left, the reason and date.

An individual may no longer be part of the active flock for these reasons:
- **Sold** — The individual is alive but has been transferred to another owner or flock.
- **Slaughtered** — The individual has been intentionally killed for meat production.
- **Deceased** — The individual has died from natural causes, accident, or illness.

In addition, some individuals exist only as **Lineage individuals** — ancestors (e.g., a sire or dam from another breeder) that were never part of the current flock manager's flock. A lineage individual has no entry or exit records. Lineage individuals must be clearly distinguishable so that they do not appear in day-to-day flock management operations (e.g., observations, interventions, batch operations) but remain visible in the genealogy graph and parentage views.

### Flock entry tracking

When an individual joins the flock, the system records:
- The entry reason (birth or purchase)
- The entry date

### Flock exit tracking

When an individual leaves the flock, the system records:
- The exit reason (sold, slaughtered, deceased)
- The exit date

## Acceptance Criteria

- Every individual is recorded as either currently part of the active flock or not.
- When an individual joins the flock, the system records an entry reason and an entry date.
- When an individual leaves the flock, the system records an exit reason and an exit date.
- A **Lineage individual** (never part of the flock, with no entry or exit records) is excluded from all day-to-day flock management views (observations, interventions, batch operations, dashboards).
- A **Lineage individual** is visible in the genealogy graph and parentage views.
- The user can view the full membership timeline (entry reason and date, exit reason and date) for any individual that has joined or left the flock.
