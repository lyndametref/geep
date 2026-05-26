# Business Requirements

An overview of all business requirements for the Geep ecosystem.

## Index

| REQ ID | Title | Group | Criticality |
|--------|-------|-------|-------------|
| [REQ-01](REQ-01.md) | Individual Management | — | — |
| [REQ-01.001](REQ-01.001.md) | Each individual must have a unique identifier | [REQ-01 Individual Management](REQ-01.md) | Must have |
| [REQ-01.002](REQ-01.002.md) | Each individual entry can have an official identifier associated. | [REQ-01 Individual Management](REQ-01.md) | Must have |
| [REQ-01.003](REQ-01.003.md) | For individual entries without an assigned BDTA number yet, the system allows later BDTA assignment. | [REQ-01 Individual Management](REQ-01.md) | Must have |
| [REQ-01.004](REQ-01.004.md) | Each individual entry includes a birth date, with an optional death date; for stillborn entries, both dates are the same. | [REQ-01 Individual Management](REQ-01.md) | Must have |
| [REQ-01.005](REQ-01.005.md) | Each individual entry includes information about the individual current state and history. | [REQ-01 Individual Management](REQ-01.md) | Must have |
| [REQ-01.006](REQ-01.006.md) | Each individual can be represented by a portrait chosen from observations or by a procedurally generated icon. | [REQ-01 Individual Management](REQ-01.md) | Should have |
| [REQ-01.007](REQ-01.007.md) | Each individual entry can include a basic color pattern | [REQ-01 Individual Management](REQ-01.md) | Should have |
| [REQ-01.008](REQ-01.008.md) | Individual flock membership | [REQ-01 Individual Management](REQ-01.md) | Must have |
| [REQ-02](REQ-02.md) | Genealogy and Parentage Graph | — | — |
| [REQ-02.001](REQ-02.001.md) | The genealogy view displays individuals as nodes of a graph | [REQ-02 Genealogy and Parentage Graph](REQ-02.md) | Must have |
| [REQ-02.002](REQ-02.002.md) | The genealogy view displays individuals as nodes with an icon and a display label. | [REQ-02 Genealogy and Parentage Graph](REQ-02.md) | Must have |
| [REQ-02.003](REQ-02.003.md) | The genealogy view displays parentage links as edges. | [REQ-02 Genealogy and Parentage Graph](REQ-02.md) | Must have |
| [REQ-02.004](REQ-02.004.md) | The genealogy view supports filtering by ancestry and life/death status. | [REQ-02 Genealogy and Parentage Graph](REQ-02.md) | Should have |
| [REQ-03](REQ-03.md) | Phenotype and Genotype Deduction | — | — |
| [REQ-03.001](REQ-03.001.md) | The sheep owner can provide phenotype information. | [REQ-03 Phenotype and Genotype Deduction](REQ-03.md) | Should have |
| [REQ-03.002](REQ-03.002.md) | The sheep owner can enter genotype information. | [REQ-03 Phenotype and Genotype Deduction](REQ-03.md) | Should have |
| [REQ-03.003](REQ-03.003.md) | The system proposes possible genotypes for an individual based on available lineage and phenotype information. | [REQ-03 Phenotype and Genotype Deduction](REQ-03.md) | Could have |
| [REQ-04](REQ-04.md) | Observations and Reproductive Planning | — | — |
| [REQ-04.001](REQ-04.001.md) | Observation types include weight evolution, health observations, and reproduction events. | [REQ-04 Observations and Reproductive Planning](REQ-04.md) | Must have |
| [REQ-04.002](REQ-04.002.md) | Batch entry allows applying the same entry to multiple selected individuals. | [REQ-04 Observations and Automated Events](REQ-04.md) | Must have |
| [REQ-04.003](REQ-04.003.md) | Individuals can be selected for batch processes through filters or manual selection. | [REQ-04 Observations and Automated Events](REQ-04.md) | Must have |
| [REQ-04.004](REQ-04.004.md) | A mating observation produces a lambing planned event at a range of 140 to 150 days after observation. | [REQ-04 Observations and Reproductive Planning](REQ-04.md) | Should have |
| [REQ-04.005](REQ-04.005.md) | A confirmed lambing proposes creation of lamb records as well as a weaning event after a configurable interval. | [REQ-04 Observations and Reproductive Planning](REQ-04.md) | Must have |
| [REQ-04.006](REQ-04.006.md) | Each individual has a chronological journal. | [REQ-04 Observations and Reproductive Planning](REQ-04.md) | Must have |
| [REQ-04.007](REQ-04.007.md) | The journal stores medical analysis results. | [REQ-04 Observations and Reproductive Planning](REQ-04.md) | Should have |
| [REQ-05](REQ-05.md) | Calendar View | — | — |
| [REQ-05.001](REQ-05.001.md) | The calendar displays past events and care. | [REQ-05 Calendar View](REQ-05.md) | Must have |
| [REQ-05.002](REQ-05.002.md) | The calendar integrates predicted future events such as lambing. | [REQ-05 Calendar View](REQ-05.md) | Must have |
| [REQ-05.003](REQ-05.003.md) | The calendar integrates reminders | [REQ-05 Calendar View](REQ-05.md) | Must have |
| [REQ-06](REQ-06.md) | Medication Management | — | — |
| [REQ-06.001](REQ-06.001.md) | Medication master data includes information about drugs. | [REQ-06 Medication Management](REQ-06.md) | Could have |
| [REQ-06.002](REQ-06.002.md) | Medication stock can be tracked by keeping a record of received and used drug as well as a stock overview. | [REQ-06 Medication Management](REQ-06.md) | Could have |
| [REQ-06.003](REQ-06.003.md) | Medication stock is automatically updated when a treatment is entered in observations. | [REQ-06 Medication Management](REQ-06.md) | Could have |
| [REQ-06.004](REQ-06.004.md) | Manual stock correction is possible. | [REQ-06 Medication Management](REQ-06.md) | Could have |
| [REQ-07](REQ-07.md) | Integrated Calculators | — | — |
| [REQ-07.001](REQ-07.001.md) | The system includes a lidocaine dosage calculator for castration. | [REQ-07 Integrated Calculators](REQ-07.md) | Could have |
| [REQ-07.002](REQ-07.002.md) | The system includes a milk or cream quantity calculator for bottle-feeding. | [REQ-07 Integrated Calculators](REQ-07.md) | Could have |
| [REQ-08](REQ-08.md) | Cheat Sheets and References | — | — |
| [REQ-08.001](REQ-08.001.md) | Standardized procedures for castration . | [REQ-08 Cheat Sheets and References](REQ-08.md) | Should have |
| [REQ-08.002](REQ-08.002.md) | Visual references include the FaMaCha color scale. | [REQ-08 Cheat Sheets and References](REQ-08.md) | Could have |
| [REQ-08.003](REQ-08.003.md) | Visual references include a Body Condition Score (BCS) scale. | [REQ-08 Cheat Sheets and References](REQ-08.md) | Should have |
| [REQ-09](REQ-09.md) | Procedural Icon Generation | — | — |
| [REQ-09.001](REQ-09.001.md) | Icon generation considers base color, agouti pattern, spotting, and dilution. | [REQ-09 Procedural Icon Generation](REQ-09.md) | Could have |
| [REQ-09.002](REQ-09.002.md) | The output can be rendered as a stylized SVG icon. | [REQ-09 Procedural Icon Generation](REQ-09.md) | Could have |
| [REQ-10](REQ-10.md) | Pasture Management and Mapping | — | — |
| [REQ-10.001](REQ-10.001.md) | The pasture view records usage history. | [REQ-10 Pasture Management and Mapping](REQ-10.md) | Should have |
| [REQ-10.002](REQ-10.002.md) | Pasture management supports parasite-oriented rotation operations. | [REQ-10 Pasture Management and Mapping](REQ-10.md) | Should have |
| [REQ-11](REQ-11.md) | Multi-Application Workflow | — | — |
| [REQ-11.001](REQ-11.001.md) | Mobile users can create and update individuals, observations, and care in the field. | [REQ-11 Multi-Application Workflow](REQ-11.md) | Must have |
| [REQ-11.002](REQ-11.002.md) | Web users can create, review, and edit records with genealogy and calendar views. | [REQ-11 Multi-Application Workflow](REQ-11.md) | Should have |
| [REQ-11.003](REQ-11.003.md) | Changes made on one application are available to other applications after synchronization. | [REQ-11 Multi-Application Workflow](REQ-11.md) | Should have |
| [REQ-12](REQ-12.md) | Multi-User Data Sharing | — | — |
| [REQ-12.001](REQ-12.001.md) | Multiple authenticated users can access the same flock dataset. | [REQ-12 Multi-User Data Sharing](REQ-12.md) | Should have |
| [REQ-12.002](REQ-12.002.md) | Updates performed by one user become visible to other authorized users. | [REQ-12 Multi-User Data Sharing](REQ-12.md) | Should have |
| [REQ-13](REQ-13.md) | Interventions and Care Management | — | — |
| [REQ-13.001](REQ-13.001.md) | Intervention types include treatment, shearing, hoof trimming, and other care actions. | [REQ-13 Interventions and Care Management](REQ-13.md) | Must have |
| [REQ-13.002](REQ-13.002.md) | Batch entry allows applying the same intervention to multiple selected individuals. | [REQ-13 Interventions and Care Management](REQ-13.md) | Must have |
| [REQ-13.003](REQ-13.003.md) | Individuals can be selected for batch interventions through filters or manual selection. | [REQ-13 Interventions and Care Management](REQ-13.md) | Must have |
| [REQ-13.004](REQ-13.004.md) | An intervention entry records quarantine periods for meat or milk. | [REQ-13 Interventions and Care Management](REQ-13.md) | Should have |
| [REQ-13.005](REQ-13.005.md) | The journal stores intervention details. | [REQ-13 Interventions and Care Management](REQ-13.md) | Should have |
| [REQ-13.006](REQ-13.006.md) | The system supports reminders for routine care interventions. | [REQ-13 Interventions and Care Management](REQ-13.md) | Must have |
| [REQ-14](REQ-14.md) | Financial Tracking | — | — |
| [REQ-14.001](REQ-14.001.md) | The system includes expenditure tracking by category | [REQ-14 Financial Tracking](REQ-14.md) | Could have |
| [REQ-14.002](REQ-14.002.md) | The system includes income tracking by source | [REQ-14 Financial Tracking](REQ-14.md) | Could have |
| [REQ-14.003](REQ-14.003.md) | The system generates profit/loss reports | [REQ-14 Financial Tracking](REQ-14.md) | Could have |
| [REQ-15](REQ-15.md) | User-Defined Event and Task Types | — | — |
| [REQ-15.001](REQ-15.001.md) | Custom observation type creation | [REQ-15 User-Defined Event and Task Types](REQ-15.md) | Should have |
| [REQ-15.002](REQ-15.002.md) | Custom intervention type creation | [REQ-15 User-Defined Event and Task Types](REQ-15.md) | Should have |
| [REQ-15.003](REQ-15.003.md) | Custom planned task type creation | [REQ-15 User-Defined Event and Task Types](REQ-15.md) | Should have |
| [REQ-15.004](REQ-15.004.md) | Custom predicted event type creation | [REQ-15 User-Defined Event and Task Types](REQ-15.md) | Could have |
| [REQ-15.005](REQ-15.005.md) | Custom waiting delay type creation | [REQ-15 User-Defined Event and Task Types](REQ-15.md) | Should have |
