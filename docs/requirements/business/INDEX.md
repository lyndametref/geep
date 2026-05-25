# Business Requirements

An overview of all business requirements for the Geep ecosystem.

## Index

| REQ ID | Title | Group | Criticality |
|--------|-------|-------|-------------|
| REQ-01 | Individual Management | — | — |
| REQ-01.001 | Each individual must have a unique identifier | REQ-01 Individual Management | Must have |
| REQ-01.002 | Each individual entry can have an official identifier associated. | REQ-01 Individual Management | Must have |
| REQ-01.003 | For individual entries without an assigned BDTA number yet, the system allows later BDTA assignment. | REQ-01 Individual Management | Must have |
| REQ-01.004 | Each individual entry includes a birth date, with an optional death date; for stillborn entries, both dates are the same. | REQ-01 Individual Management | Must have |
| REQ-01.005 | Each individual entry includes information about the individual current state and history. | REQ-01 Individual Management | Must have |
| REQ-01.006 | Each individual can be represented by a portrait chosen from observations or by a procedurally generated icon. | REQ-01 Individual Management | Should have |
| REQ-01.007 | Each individual entry can include a basic color pattern | REQ-01 Individual Management | Should have |
| REQ-01.008 | Individual flock membership | REQ-01 Individual Management | Must have |
| REQ-02 | Genealogy and Parentage Graph | — | — |
| REQ-02.001 | Genealogy data is stored in a graph structure. | REQ-02 Genealogy and Parentage Graph | Must have |
| REQ-02.002 | The genealogy view displays individuals as nodes with an icon and a display label. | REQ-02 Genealogy and Parentage Graph | Must have |
| REQ-02.003 | The genealogy view displays parentage links as edges. | REQ-02 Genealogy and Parentage Graph | Must have |
| REQ-02.004 | The genealogy view supports filtering by ancestry and life/death status. | REQ-02 Genealogy and Parentage Graph | Should have |
| REQ-03 | Phenotype and Genotype Deduction | — | — |
| REQ-03.001 | The sheep owner can provide phenotype information. | REQ-03 Phenotype and Genotype Deduction | Should have |
| REQ-03.002 | The sheep owner can enter genotype information. | REQ-03 Phenotype and Genotype Deduction | Should have |
| REQ-03.003 | The system proposes possible genotypes for an individual based on available lineage and phenotype information. | REQ-03 Phenotype and Genotype Deduction | Could have |
| REQ-04 | Observations and Reproductive Planning | — | — |
| REQ-04.001 | Observation types include weight evolution, health observations, and reproduction events. | REQ-04 Observations and Reproductive Planning | Must have |
| REQ-04.002 | Batch entry allows applying the same entry to multiple selected individuals. | REQ-04 Observations and Automated Events | Must have |
| REQ-04.003 | Individuals can be selected for batch processes through filters or manual selection. | REQ-04 Observations and Automated Events | Must have |
| REQ-04.004 | A mating observation produces a lambing planned event at a range of 140 to 150 days after observation. | REQ-04 Observations and Reproductive Planning | Should have |
| REQ-04.005 | A confirmed lambing proposes creation of lamb records as well as a weaning event after a configurable interval. | REQ-04 Observations and Reproductive Planning | Must have |
| REQ-04.006 | Each individual has a chronological journal. | REQ-04 Observations and Reproductive Planning | Must have |
| REQ-04.007 | The journal stores medical analysis results. | REQ-04 Observations and Reproductive Planning | Should have |
| REQ-05 | Calendar View | — | — |
| REQ-05.001 | The calendar displays past events and care. | REQ-05 Calendar View | Must have |
| REQ-05.002 | The calendar integrates predicted future events such as lambing. | REQ-05 Calendar View | Must have |
| REQ-05.003 | The calendar integrates reminders | REQ-05 Calendar View | Must have |
| REQ-06 | Medication Management | — | — |
| REQ-06.001 | Medication master data includes information about drugs. | REQ-06 Medication Management | Could have |
| REQ-06.002 | Medication stock can be tracked by keeping a record of received and used drug as well as a stock overview. | REQ-06 Medication Management | Could have |
| REQ-06.003 | Medication stock is automatically updated when a treatment is entered in observations. | REQ-06 Medication Management | Could have |
| REQ-06.004 | Manual stock correction is possible. | REQ-06 Medication Management | Could have |
| REQ-07 | Integrated Calculators | — | — |
| REQ-07.001 | The system includes a lidocaine dosage calculator for castration. | REQ-07 Integrated Calculators | Could have |
| REQ-07.002 | The system includes a milk or cream quantity calculator for bottle-feeding. | REQ-07 Integrated Calculators | Could have |
| REQ-08 | Cheat Sheets and References | — | — |
| REQ-08.001 | Standardized procedures for castration . | REQ-08 Cheat Sheets and References | Should have |
| REQ-08.002 | Visual references include the FaMaCha color scale. | REQ-08 Cheat Sheets and References | Could have |
| REQ-08.003 | Visual references include a Body Condition Score (BCS) scale. | REQ-08 Cheat Sheets and References | Should have |
| REQ-09 | Procedural Icon Generation | — | — |
| REQ-09.001 | Icon generation considers base color, agouti pattern, spotting, and dilution. | REQ-09 Procedural Icon Generation | Could have |
| REQ-09.002 | The output can be rendered as a stylized SVG icon. | REQ-09 Procedural Icon Generation | Could have |
| REQ-10 | Pasture Management and Mapping | — | — |
| REQ-10.001 | The pasture view records usage history. | REQ-10 Pasture Management and Mapping | Should have |
| REQ-10.002 | Pasture management supports parasite-oriented rotation operations. | REQ-10 Pasture Management and Mapping | Should have |
| REQ-11 | Multi-Application Workflow | — | — |
| REQ-11.001 | Mobile users can create and update individuals, observations, and care in the field. | REQ-11 Multi-Application Workflow | Must have |
| REQ-11.002 | Web users can create, review, and edit records with genealogy and calendar views. | REQ-11 Multi-Application Workflow | Should have |
| REQ-11.003 | Changes made on one application are available to other applications after synchronization. | REQ-11 Multi-Application Workflow | Should have |
| REQ-12 | Multi-User Data Sharing | — | — |
| REQ-12.001 | Multiple authenticated users can access the same flock dataset. | REQ-12 Multi-User Data Sharing | Should have |
| REQ-12.002 | Updates performed by one user become visible to other authorized users. | REQ-12 Multi-User Data Sharing | Should have |
| REQ-13 | Interventions and Care Management | — | — |
| REQ-13.001 | Intervention types include treatment, shearing, hoof trimming, and other care actions. | REQ-13 Interventions and Care Management | Must have |
| REQ-13.002 | Batch entry allows applying the same intervention to multiple selected individuals. | REQ-13 Interventions and Care Management | Must have |
| REQ-13.003 | Individuals can be selected for batch interventions through filters or manual selection. | REQ-13 Interventions and Care Management | Must have |
| REQ-13.004 | An intervention entry records quarantine periods for meat or milk. | REQ-13 Interventions and Care Management | Should have |
| REQ-13.005 | The journal stores intervention details. | REQ-13 Interventions and Care Management | Should have |
| REQ-13.006 | The system supports reminders for routine care interventions. | REQ-13 Interventions and Care Management | Must have |
| REQ-14 | Financial Tracking | — | — |
| REQ-14.001 | The system includes expenditure tracking by category | REQ-14 Financial Tracking | Could have |
| REQ-14.002 | The system includes income tracking by source | REQ-14 Financial Tracking | Could have |
| REQ-14.003 | The system generates profit/loss reports | REQ-14 Financial Tracking | Could have |
| REQ-15 | User-Defined Event and Task Types | — | — |
| REQ-15.001 | Custom observation type creation | REQ-15 User-Defined Event and Task Types | Should have |
| REQ-15.002 | Custom intervention type creation | REQ-15 User-Defined Event and Task Types | Should have |
| REQ-15.003 | Custom planned task type creation | REQ-15 User-Defined Event and Task Types | Should have |
| REQ-15.004 | Custom predicted event type creation | REQ-15 User-Defined Event and Task Types | Could have |
| REQ-15.005 | Custom waiting delay type creation | REQ-15 User-Defined Event and Task Types | Should have |
