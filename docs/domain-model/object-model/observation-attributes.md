# Observation — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Observation is a specialization of Record. Attributes marked as inherited from Record are documented in full in `record-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from Record (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | BR-009 |
| `recordType` | Enum | Yes | Fixed to `OBSERVATION` for Observation instances. Discriminator for the journal entry subtype. | REQ-04, BR-009 |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Represents when the record was logged in the system. Used for chronological journal ordering. | REQ-04, REQ-04.006 |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set (BR-018). A record belongs to exactly one individual. | REQ-04, BR-018, ADR-0001 |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event (BR-015). | BR-015 |

## Observation-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp (e.g. `2024-06-01T10:00:00Z`). Represents when the observation actually occurred, which may differ from `recordedAt` (the log time). | REQ-04 |
| `content` | JSON (Structured Data) | Yes | Type-specific observation payload. Contains an `observationType` field (discriminator for the kind of observation, see below) along with type-specific data. The type list is extensible per flock (REQ-04.001, REQ-15.001). | REQ-04, REQ-04.001, REQ-04.007, BR-019 |

## Observation types

The `observationType` field in `content` discriminates the kind of observation. Below are the built-in types with their required content fields. Custom types (REQ-15.001) may define additional fields beyond those listed here.

### WEIGHT

Records a body weight measurement.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"WEIGHT"`. | REQ-04.001 |
| `weightKg` | Number | Yes | Body weight in kilograms. Must be positive. | REQ-04.001 |
| `notes` | String | No | Optional notes. | |

### MATING_MARK

Records a mating mark observation — visual confirmation that a ewe has been mounted by a ram.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"MATING_MARK"`. | REQ-04.001, REQ-04.004 |
| `sireIds` | Array of Long | No | FK(s) to sire Individual(s). Each must be Male (BR-005). Zero or more — empty when the ram is unknown, multiple when the ewe was with a group of rams. | REQ-04.001, REQ-04.004 |

### LAMBING

Records a lambing event for a ewe.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"LAMBING"`. | REQ-04.001, REQ-04.005 |
| `numberOfLambs` | Integer | Yes | Total number of lambs born. Must be positive. | REQ-04.005, BR-022 |
| `lambs` | Array | No | Details per lamb. Each entry: `{ "sex": "Male" \| "Female" }`. Length must equal `numberOfLambs` if provided. | REQ-04.005 |

### HEALTH

Records a health observation (illness, injury, condition).

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"HEALTH"`. | REQ-04.001 |
| `condition` | String | Yes | Free-text description of the health condition. | REQ-04.001 |
| `notes` | String | No | Additional notes. | |

### NOTE

A free-form journal note not covered by other types.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"NOTE"`. | REQ-04 |
| `text` | String | Yes | Free-form text content. | REQ-04 |

### FECAL_EGG_COUNT

Records a fecal egg count analysis result. Other medical analysis types can be created by the flock manager as custom observation types (REQ-15.001). (REQ-04.007)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"FECAL_EGG_COUNT"`. | REQ-04.007 |
| `parasite` | Enum | Yes | Values: `"STRONGYLES"`, `"NEMATODIRUS"`, `"COCCIDIA"`, `"LIVER_FLUKE"`. | REQ-04.007 |
| `result` | String | Yes | Result value or description (e.g. eggs per gram). | REQ-04.007 |
| `notes` | String | No | Additional notes. | |

### BODY_CONDITION_SCORE

Records a Body Condition Score assessment. (REQ-08.003)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"BODY_CONDITION_SCORE"`. | REQ-08.003 |
| `score` | Number | Yes | Body Condition Score. Values: 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0 (half-point increments). | REQ-08.003 |
| `notes` | String | No | Additional notes. | |

### FAMACHA

Records a FAMACHA anemia score based on conjunctival color. (REQ-08.002)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"FAMACHA"`. | REQ-08.002 |
| `score` | Integer | Yes | Values: 1 (red, no anemia) to 5 (white, severe anemia). | REQ-08.002 |
| `notes` | String | No | Additional notes. | |

### FLOCK_ENTRY

Records an individual's entry into the flock.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"FLOCK_ENTRY"`. | REQ-01.008, BR-019 |
| `reason` | Enum | Yes | Values: `"BIRTH"`, `"PURCHASE"`. | REQ-01.008, BR-019 |
| `originFarmId` | String | No | Identifier of the farm the individual came from. Relevant when `reason` is `PURCHASE`. | REQ-01.008 |
| `originFarmName` | String | No | Name of the origin farm. Relevant when `reason` is `PURCHASE`. | REQ-01.008 |
| `notes` | String | No | Additional notes. | |

### FLOCK_EXIT

Records an individual's exit from the flock.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observationType` | String | Yes | Fixed to `"FLOCK_EXIT"`. | REQ-01.008, BR-019 |
| `reason` | Enum | Yes | Values: `"SOLD"`, `"SLAUGHTERED"`, `"DECEASED"`. | REQ-01.008, BR-019 |
| `destinationFarmId` | String | No | Identifier of the farm the individual was sold to. Relevant when `reason` is `SOLD`. | REQ-01.008 |
| `destinationFarmName` | String | No | Name of the destination farm. Relevant when `reason` is `SOLD`. | REQ-01.008 |
| `notes` | String | No | Additional notes. | |

