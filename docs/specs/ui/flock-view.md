# Flock view

## Scope

The flock view lists all individuals with search and filter capabilities. It is the primary entry point for accessing individual journals and performing batch operations.

## Layout

Scrollable list of individual cards. Each card displays:

- **Display label** (resolved per `genealogy-display-label.md`)
- **Sex icon** (male/female)
- **Birth year**
- **Alive/dead indicator**
- **Flock status** (active/exited)

## Search and filter

**Search bar** at the top — filters by display label or official ID as the user types.

**Filter chips** below the search bar:

| Filter | Options |
|--------|---------|
| Sex | All, Male, Female |
| Age | All, <1 year, 1–3 years, 3–5 years, 5+ years |
| Flock status | All, Active, Exited |

Multiple filters can be combined. Clearing all filters shows the full flock.

## Actions

- **Tap** a card — navigate to the individual's journal (see `journal-view.md`)
- **Long press** a card — context menu (edit individual, view genealogy, add to batch)
- **Floating action button** — add a new individual
- **Batch icon** in the header — enter batch selection mode

## Batch mode

- Tapping the batch icon enters selection mode
- Checkboxes appear on individual cards
- Bottom bar shows "Add observation" / "Add intervention" / "Add Planned Task" actions
- After selecting type and filling form, one record is created per selected individual (see ADR-0001)

## Individual detail

Tapping a card opens the individual's journal as the default view. Swipeable tabs provide access to:

| Tab | Content |
|-----|---------|
| **Journal** | Journal view (see `journal-view.md`) |
| **Info** | Individual detail (see `individual-view.md`) |
| **Calendar** | Calendar view filtered to this individual (see `calendar-view.md`) |
