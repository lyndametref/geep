# Individual detail

## Scope

The individual detail view shows the core information for a single individual. It is one of the swipeable tabs in the flock view alongside Journal and Calendar.

## Layout

Scrollable card-based layout with sections.

## Sections

### Identity card

| Field | Source |
|-------|--------|
| Display label | Computed per `genealogy-display-label.md` |
| Official ID | `individual.officialId` |
| Sex | `individual.sex` |
| Breed | `individual.breed` |
| Birth date | `individual.birthDate` |
| Death date | `individual.deathDate` (if set) |
| Flock status | Active / Exited (from BR-019) |

### Parentage

| Field | Source |
|-------|--------|
| Sire (father) | `individual.sireId` — tappable, navigates to sire's detail |
| Dam (mother) | `individual.damId` — tappable, navigates to dam's detail |

### Quick actions

- **Edit** — opens edit form for modifiable fields
- **View genealogy** — navigates to genealogy view for this individual

## Edit form

Tapping Edit opens a form with editable fields:

| Field | Required | Notes |
|-------|----------|-------|
| Display name | No | Free-text, optional friendly name |
| Sex | Yes | Male / Female |
| Breed | No | Free-text |
| Birth date | Yes | Date picker |
| Death date | No | Date picker, sets individual as deceased |
| Sire | No | Searchable individual selector, male only |
| Dam | No | Searchable individual selector, female only |

Save updates the individual. Cancel discards changes.
