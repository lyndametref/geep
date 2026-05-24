# Observation — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Observation is a specialization of Record. Attributes marked as inherited from Record are documented in full in `record-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from Record (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | BR-009 |
| `recordType` | Enum | Yes | Fixed to `OBSERVATION` for Observation instances. Discriminator for the journal entry subtype. | REQ-04, BR-009 |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Represents when the record was logged in the system. Used for chronological journal ordering. | REQ-04, BR-016 |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set (BR-018). A record belongs to exactly one individual. | REQ-04, BR-018, ADR-0001 |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event (BR-015). | BR-015 |

## Observation-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `observedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp (e.g. `2024-06-01T10:00:00Z`). Represents when the observation actually occurred, which may differ from `recordedAt` (the log time). | REQ-04 |
| `content` | JSON (Structured Data) | Yes | Type-specific observation payload. Contains an `observationType` field (discriminator for the kind of observation, e.g. `WEIGHT`, `MATING`, `BIRTH`, `HEALTH`, `FREE_TEXT`, `MEDICAL_ANALYSIS`, `FLOCK_ENTRY`, `FLOCK_EXIT`) along with type-specific data. The type list is extensible per flock (REQ-04.001). | REQ-04, REQ-04.001, REQ-04.007, BR-019 |

