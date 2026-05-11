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
  - The system must support handling for stillborn lambs with the same basic properties as alive born(date, parents, weight, and color). However, outside those basic parameters no more observation are possible. A BDTA number association is possible.
  - The system must associate each individual with a visual representation.
- Acceptance Criteria:
  - Each individual record includes a unique internal identifier.
  - For registered animals, each individual record includes a unique BDTA number.
  - For records without an assigned BDTA number yet (for example newborn or stillborn records), the system allows later BDTA assignment.
  - Each individual record includes birth date and death date when available. For stillborn, both date are the same.
  - Each individual record includes sex, basic color pattern (complete phenotyping is separated and included in FR-003), parents, and alive/dead status.
  - Each individual can be represented by a portrait chosen from observations or by a procedurally generated icon.

### 2. FR-002 Genealogy and Parentage Graph
- User Story: As a sheep owner, I want to explore parentage relationships visually so that I can understand ancestry and inherited characteristics quickly.
- Description:
  - The system must store genealogy data as a graph structure.
  - The system must provide an interactive genealogy visualization.
  - Out of scope: disputed parentage (multiple sire with dam during tupping season)
- Acceptance Criteria:
  - Genealogy data is stored in a graph structure.
  - The genealogy view displays individuals as nodes with an icon and name or number.
  - The genealogy view displays parentage links as edges.
  - The genealogy view supports filtering by ancestry (all descendant of, all ancester of, brothers/sisters) and live status.

### 3. FR-003 Phenotype and Genotype Deduction
- User Story: As a sheep owner, I want provide the phenotype of my sheed and get possible genotype propositions so that I can reason about inherited trait and deduct genotype with recommendation of the system.
- Description:
  - The system must support phenotype, and genotype deduction.
- Acceptance Criteria:
  - The phenotype can be provided by the sheep owner.
  - The genotype can be entered by the sheep owner. When not yet certain, the genotype can be marked as unconfirmed. In this state, multiple allele can be provided for a given gene.
  - Based on genotype of the ancestry, the phenotype, and the phenotype of the descendants, propositions for the genotype of the individual are made.
  - Detailed business rules for deduction remain to be specified.

### 4. FR-004 Observations and Automated Events
- User Story: As a sheep owner, I want to record observations and trigger events so that I can maintain health, and reproduction traceability.
- Description:
  - The system must record observations related to weight, health, and reproduction.
  - The system must support batch entry for observations and treatments.
  - The system must derive reminders and lifecycle events from recorded observations.
  - The system must keep a chronological journal per individual, which can be easily consulted by the sheep owner.
- Acceptance Criteria:
  - Observation types include weight evolution, health observations, and reproduction events.
  - Batch entry allows applying the same observation or treatment to multiple selected individuals.
  - Individuals can be selected through filters such as group, age, and sex, or through manual selection.
  - A mating observation produces a birth planned event at a range of 140 to 150 days after observation.
  - A treatment observation records a quarantine period for meat or milk.
  - A confirmed birth proposes creation of lamb records as well as a weaning event 3 months later.
  - Each individual has a chronological journal with attachments such as photos and PDFs.
  - The journal stores treatment dates, doses, and quarantine reminders.
  - The journal stores medical analysis results.
  - The system supports reminders for start of heat period, hoof trimming, vaccines, and shearing.

### 5. FR-005 Calendar View
- User Story: As a sheep owner, I want a calendar view of upcoming activities and predicted events so that I can plan flock operations, as well as for past activities.
- Description:
  - The system must provide a calendar view for planned events, treatments, and reminders as well as past events and activities.
- Acceptance Criteria:
  - The calendar displays past events such as births, treatments, and weaning.
  - The calendar integrates predicted futur event such as birth.
  - The calendar integrates reminder such as weaning.

### 6. FR-006 Medication Management
- User Story: As a sheep owner, I want to manage medications and stock so that treatments remain traceable and available.
- Description:
  - The system must maintain a user-managed medication master data.
  - The system must track medication stock.
  - When a treatment for an animal is entered in observation, the amount of medicine in stocke is automatically updated.
  - Manual correction of stock amount is possible, for example to delete leftovers.
- Acceptance Criteria:
  - Medications master data  include a name and quarantine period for each medication.
  - Medication stock can be tracked by keeping a record of received and used medication as well as a stock overview.
  - Medication stock is automatically updated when a treatment is entered in observations.
  - Manual edition of stock is possible.

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
  - The system may provide a pasture mapping view for rotation management.
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
  - Web users can create, review and edit ecords with genealogy and calendar views.
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