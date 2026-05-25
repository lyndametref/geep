# Business Glossary

## Purpose
Canonical glossary for business terms used across requirements, domain model, specifications, and backlog.

## Entry Conventions
- Keep terms in alphabetical order.
- Use singular noun forms when possible.
- Keep definitions short, precise, and domain-focused.
- Avoid implementation details in definitions.

## Terms

| Term | Definition |
|------|------------|
| Ancestor | A preceding Individual in lineage, reachable through parent (sire/dam) relationships. |
| Attachment | Documentary evidence (photo, PDF) linked to a Record. |
| Batch Entry | The capability to apply the same Observation or Intervention to multiple selected Individuals in one operation. |
| BCS | Body Condition Score. |
| BDTA/TVD | Swiss Animal identifier (a form of Official Identifier) referenced in the individual management requirements. |
| Castration | An Intervention type. Can only be performed on male lamb before their 14th day. |
| Color Pattern | Visual coat pattern or color describing an Individual. |
| Dam | The female parent of an Individual. |
| Descendant | A succeeding Individual in lineage, reachable through child relationships. |
| Dose | The quantity of drug administered to an individual. |
| Ear Tag | An alphanumeric identifier assigned to an Individual, typically displayed on a physical ear tag, used for farm-level quick-reference identification. Distinct from the regulatory Official Identifier. |
| FaMaCha | Visual scoring method based on mucous membrane color. |
| Flock | A group of sheep managed together under a single owner or manager. |
| Flock Entry | The joining of an Individual into the Flock, with a recorded reason (birth or purchase) and date. |
| Flock Exit | The departure of an Individual from the Flock, with a recorded reason (sold, slaughtered, deceased) and date. |
| Flock Manager | The user role responsible for managing the flock and configuring system options. |
| Flock Member | An Individual that is currently part of the Flock Manager's active flock. |
| Future Event | A derived event or reminder that is planned, predicted, waiting, realized, or aborted, depending on context.|
| Genealogy Graph | A graph structure where Individuals are nodes and parentage relationships are edges, enabling ancestry-based reasoning and navigation. |
| Genotype | Genetic makeup of an Individual. |
| Genotype Deduction | System-proposed possible genotypes for an Individual based on ancestry, phenotype, and descendant phenotype. |
| Hoof Trimming | An Intervention for hoof care. |
| Individual | A sheep managed or referenced in the system. |
| Intervention | A type of Record capturing performed actions, care, and treatment information (e.g., shearing, hoof trimming, treatment administration). |
| Journal | A chronological record of Observations and Interventions associated with one or more Individuals. |
| Lambing | The process of giving birth in a ewe. |
| Lineage Individual | An Individual that exists in the system for genealogy purposes but was never part of the current flock manager's active flock. |
| Meat Quarantine Period | The withdrawal period after treatment during which meat from the treated animal must not be used. |
| Medical Analysis Result | A health analysis result (e.g., lab test) stored as an Observation in an Individual's journal. |
| Membership Timeline | The chronological sequence of FLOCK_ENTRY and FLOCK_EXIT Observations for an Individual relative to the Flock. |
| Medication / Drug | A substance used in treatments. |
| Medication Stock | Tracked inventory of drugs. |
| Milk Quarantine Period | The withdrawal period after treatment during which milk from the treated animal must not be used. |
| Pasture Movement | The transfer of a batch of Individuals to or from a Pasture. |
| Observation | A type of Record capturing observed information about the individual. For example: weight measurements, health observations, medical analysis results, and reproduction events like mating or birth. |
| Official Identifier | An externally-regulated animal identifier (e.g., AMD/TVD/BDTA in Switzerland) that can be assigned to an Individual. |
| Parentage Graph | Interactive representation of ancestor and descendant relationships. |
| Parentage Link | An edge connecting two Individuals, representing a sire-child or dam-child relationship. |
| Pasture | A grazing land parcel whose usage is tracked for rotation and parasite management. |
| Pasture Rotation | The practice of moving Individuals between Pastures to control parasite exposure. |
| Phenotype | Observable physical characteristics of an Individual (e.g., coat color, pattern). |
| Planned Task | A task to be performed in the future at a due date (e.g., weaning). |
| Predicted Birth Date | The predicted date of lambing. |
| Predicted Event | A Future Event representing a probabilistic outcome derived from prior records (e.g., a predicted birth window). A specific case is a Predicted Birth Date. |
| Quarantine Period | Withdrawal period after treatment for meat or milk usage. |
| Recovery Quarantine | A rest period after a Pasture becomes vacant before it can be used again. |
| Record | The journal entry. |
| Reminder | A notification about planned task, such as hoof trimming or shearing. |
| Shearing | Intervention to remove wool from a sheep. Is mandatory for every sheep every year.|
| Sire | The male parent of an Individual. |
| Stillborn | An Individual born dead. |
| Treatment | An Intervention that administers medication to one or more Individuals. |
| Waiting Delay | A delay interval that must elapse before normal operations resume (e.g., quarantine after treatment). |
| Weaning | Intervention to transition lambs from milk to solid food. |
