# Intervention — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Intervention is a specialization of Record. Attributes marked as inherited from Record are documented in full in `record-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from Record (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | BR-009 |
| `recordType` | Enum | Yes | Fixed to `INTERVENTION` for Intervention instances. Discriminator for the journal entry subtype. | REQ-13, BR-009 |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Represents when the record was logged in the system. Used for chronological journal ordering. | REQ-13, REQ-04.006 |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set (BR-018). A record belongs to exactly one individual. | REQ-13, BR-018, ADR-0001 |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event (BR-015). | BR-015 |

## Intervention-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `performedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp (e.g. `2024-06-01T10:00:00Z`). Represents when the intervention was actually performed, which may differ from `recordedAt` (the log time). | REQ-13.005 |
| `content` | JSON (Structured Data) | Yes | Type-specific intervention payload. Structure defined in `docs/specs/data-type/intervention-content-schema.json`. The type list is extensible per product evolution (REQ-13.001). Custom intervention types can be defined per-flock (REQ-15.002). | REQ-13.001, REQ-13.005, REQ-15.002 |

## Intervention types

The `interventionType` field in `content` discriminates the kind of intervention. Below are the built-in types with their required content fields. Custom types (REQ-15.002) may define additional fields beyond those listed here.

### MEDICATION

A medication-based intervention (treatment) with dose and optional withdrawal periods. When withdrawal periods are provided, the system derives Waiting Delay entries for quarantine tracking (see BR-007, BR-014).

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `interventionType` | String | Yes | Fixed to `"MEDICATION"`. | REQ-13.001, REQ-13.005 |
| `medication` | String | Yes | Free-text medication name or identifier. May be superseded by a medication product reference in future versions. | REQ-13.005 |
| `dose` | String | Yes | Dose description (e.g. quantity, concentration, administration route). Free-text for flexibility across drug forms. | REQ-13.005 |
| `administrationRoute` | String | No | Route of administration. Free-text (e.g. `"ORAL"`, `"INJECTABLE_SUBCUTANEOUS"`, `"INTRAMUSCULAR"`, `"TOPICAL"`). | REQ-13.005 |
| `meatWithdrawalDays` | Integer | No | Number of days before meat from the animal is safe for consumption. Must be non-negative if provided. When set, triggers creation of a Waiting Delay for meat quarantine (BR-007, BR-014). | REQ-13.004, REQ-13.005 |
| `milkWithdrawalDays` | Integer | No | Number of days before milk from the animal is safe for consumption. Must be non-negative if provided. When set, triggers creation of a Waiting Delay for milk quarantine (BR-007, BR-014). | REQ-13.004, REQ-13.005 |
| `notes` | String | No | Optional notes. | |

### SHEARING

Records a shearing event — wool removal from a sheep.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `interventionType` | String | Yes | Fixed to `"SHEARING"`. | REQ-13.001 |
| `notes` | String | No | Optional notes. | |

### HOOF_TRIMMING

Records a hoof trimming intervention.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `interventionType` | String | Yes | Fixed to `"HOOF_TRIMMING"`. | REQ-13.001 |
| `notes` | String | No | Optional notes. | |

### CASTRATION

Records a castration intervention performed on a male lamb. Business rule: Castration can only be performed on male lamb before their 14th day (see business glossary).

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `interventionType` | String | Yes | Fixed to `"CASTRATION"`. | REQ-13.001 |
| `method` | String | No | Method used. Allowed values: `"BURRDOZZO"`, `"SURGICAL"`, `"ELASTIC_RING"`. | REQ-13.001 |
| `notes` | String | No | Optional notes. | |

### WEANING

Records a weaning intervention for the lamb — separation from maternal milk, transition to solid food.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `interventionType` | String | Yes | Fixed to `"WEANING"`. | REQ-13.001 |
| `weightKg` | Number | No | Lamb weight at weaning in kilograms. Must be positive if provided. | REQ-13.001 |
| `notes` | String | No | Optional notes. | |

### DRY_OFF

Records a dry-off intervention for the ewe — ending the lactation period.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `interventionType` | String | Yes | Fixed to `"DRY_OFF"`. | REQ-13.001 |
| `reason` | String | No | Reason for dry-off. Free-text (e.g. `"PREGNANCY"`, `"END_OF_LACTATION"`, `"HEALTH"`). | REQ-13.001 |
| `notes` | String | No | Optional notes. | |

