# REQ-02.002: The genealogy view displays individuals as nodes with an icon and a display label.

As a sheperd, I want each animal to be clearly identifiable in the genealogy view so that I can navigate lineage quickly.

Group: REQ-02 Genealogy and Parentage Graph
Criticality Must have

## Description
The genealogy view must render individuals as nodes and display, for each node, an icon and a display label. The display label should use the best available identifier in this order: name, official number, then a generated fallback label derived from the immutable internal ID.

## Acceptance Criteria
- The genealogy view renders individuals as nodes.
- Each displayed node includes an icon.
- Each displayed node includes a display label.
- If a name exists, the display label uses the name.
- If no name exists and an official number exists, the display label uses the official number.
- If neither name nor official number exists, the display label uses a generated fallback based on the immutable internal ID.
