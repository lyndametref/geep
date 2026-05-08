# Requirements: Ovine Flock Management Software

## Main Goals
- Centralize management of individual data (genealogy, health, observations).
- Automate reminders and events related to life cycles (births, weaning, treatments).
- Facilitate genetic analysis (colors, parentage) and health traceability.
 
## Functional Requirements

### 1. FR-001 Individual Management
- User Story: As a sheep owner, I want to manage complete records for each sheep so that I can track identity, lifecycle, and lineage.
- Description:
  - The system must manage individual records with identity and lifecycle data.
  - The system must support special handling for stillborn lambs.
  - The system must associate each individual with a visual representation.
- Acceptance Criteria:
  - Each individual record includes a unique internal identifier.
  - For registered animals, each individual record includes a unique BDTA number.
  - For records without an assigned BDTA number yet (for example newborn or stillborn records), the system allows later BDTA assignment.
  - Each individual record includes birth date and death date when available.
  - Each individual record includes sex, color, parents, and alive/dead status.
  - Stillborn lamb records support limited information including date, parents, weight, and color.
  - Each individual can be represented by a portrait chosen from observations or by a procedurally generated icon.

### 2. FR-002 Genealogy and Parentage Graph
- User Story: As a sheep owner, I want to explore parentage relationships visually so that I can understand ancestry and descendants quickly.
- Description:
  - The system must store genealogy data as a graph structure.
  - The system must provide an interactive genealogy visualization.
- Acceptance Criteria:
  - Genealogy data is stored in a graph structure.
  - The genealogy view displays individuals as nodes with an icon and name or number.
  - The genealogy view displays parentage links as edges.
  - The genealogy view supports filtering by generation, color, or status.

### 3. FR-003 Phenotype and Genotype Deduction
- User Story: As a sheep owner, I want phenotype and genotype deduction support so that I can reason about inherited traits.
- Description:
  - The system must support phenotype and genotype deduction.
- Acceptance Criteria:
  - The feature is represented in the product scope as a dedicated capability.
  - Detailed business rules for deduction remain to be specified.

### 4. FR-004 Observations and Automated Events
- User Story: As a sheep owner, I want to record observations and trigger lifecycle events so that I can maintain health and reproduction traceability.
- Description:
  - The system must record observations related to weight, health, and reproduction.
  - The system must support batch entry for observations and treatments.
  - The system must derive reminders and lifecycle events from recorded observations.
  - The system must keep a chronological journal per individual.
- Acceptance Criteria:
  - Observation types include weight evolution, health observations, and reproduction events.
  - Batch entry allows applying the same observation or treatment to multiple selected individuals.
  - Individuals can be selected through filters such as group, age, and sex, or through manual selection.
  - A mating observation produces a birth reminder at 140 days.
  - A treatment observation records a quarantine period for meat or milk.
  - A confirmed birth proposes creation of lamb records and a weaning event at 3 months.
  - Each individual has a chronological journal with attachments such as photos and PDFs.
  - The journal stores treatment dates, doses, and quarantine reminders.
  - The system supports reminders for start of heat period, hoof trimming, vaccines, and shearing.

### 5. FR-005 Calendar View
- User Story: As a sheep owner, I want a calendar view of upcoming activities so that I can plan flock operations.
- Description:
  - The system must provide a calendar view for planned events, treatments, and reminders.
- Acceptance Criteria:
  - The calendar displays planned births, treatments, and reminders.
  - The calendar integrates observation-derived events and alerts.

### 6. FR-006 Medication Management
- User Story: As a sheep owner, I want to manage medications and stock so that treatments remain traceable and available.
- Description:
  - The system must maintain a user-managed medication database.
  - The system must track medication stock.
- Acceptance Criteria:
  - Medications include a managed name and quarantine period.
  - Medication stock can be tracked.

### 7. FR-007 Integrated Calculators
- User Story: As a sheep owner, I want built-in calculators so that I can make routine husbandry decisions consistently.
- Description:
  - The system must provide calculators for common flock-management tasks.
- Acceptance Criteria:
  - The system includes a lidocaine dosage calculator for castration.
  - The system includes a milk or cream quantity calculator for bottle-feeding.
  - The system includes a reproductive cycle date calculator.
  - The system includes pasture management support for parasite control.

### 8. FR-008 Cheat Sheets and References
- User Story: As a sheep owner, I want quick-access reference material so that I can apply standard procedures consistently.
- Description:
  - The system must provide cheat sheets and visual references.
- Acceptance Criteria:
  - Standardized procedures include castration and BCS measurement.
  - Visual references include the FaMaCha color scale and spotting pattern examples.

### 9. FR-009 Procedural Icon Generation
- User Story: As a sheep owner, I want each sheep to have a distinct visual identity so that phenotype differences are easier to recognize.
- Description:
  - The system must provide generation of a unique icon for each sheep based on phenotype.
- Acceptance Criteria:
  - Icon generation considers base color, agouti pattern, spotting, and dilution.
  - The output can be rendered as a stylized SVG icon.

### 10. FR-010 Pasture Mapping View
- User Story: As a sheep owner, I want to track pasture usage so that I can manage parasite exposure through rotation.
- Description:
  - The system may provide a pasture mapping view for rotation management as an optional feature.
- Acceptance Criteria:
  - The pasture view records usage history.
  - The pasture view supports parasite-management-oriented rotation tracking.

### 11. FR-011 Multi-Application Workflow
- User Story: As a sheep owner, I want to capture data on mobile and review it on web so that field work and office work stay consistent.
- Description:
  - The system must provide a mobile application for field data collection.
  - The system must provide a web application for visualization and correction/completion of collected data.
  - The system must keep records consistent across applications.
- Acceptance Criteria:
  - Mobile users can create and update individuals, observations, and treatments in the field.
  - Web users can review and edit the same records with genealogy and calendar views.
  - Changes made on one application are available to other authorized users after synchronization.

### 12. FR-012 Multi-User Data Sharing
- User Story: As a flock manager, I want data shared across multiple users so that my team works on a single source of truth.
- Description:
  - The backend must consolidate data and distribute it across multiple users.
  - The system must restrict data access to authenticated users.
- Acceptance Criteria:
  - Multiple authenticated users can access the same flock dataset.
  - Updates performed by one user become visible to other authorized users.
  - Unauthorized users cannot access flock data.