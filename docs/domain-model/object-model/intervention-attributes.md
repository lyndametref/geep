# Intervention — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Intervention is a specialization of Record. Attributes marked as inherited from Record are documented in full in `record-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from Record (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | BR-009 |
| `recordType` | Enum | Yes | Fixed to `INTERVENTION` for Intervention instances. Discriminator for the journal entry subtype. | REQ-13, BR-009 |
| `recordedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp ending with `Z` (e.g. `2024-06-01T10:00:00Z`). Represents when the record was logged in the system. Used for chronological journal ordering. | REQ-13, BR-016 |
| `individualId` | Long | Yes | Logical FK to Individual. Mandatory and immutable once set (BR-018). A record belongs to exactly one individual. | REQ-13, BR-018, ADR-0001 |
| `sourceFutureEventId` | Long | No | Logical FK to the FutureEvent that this Record realizes. Populated when a record is created as the realization of a planned or predicted event (BR-015). | BR-015 |

## Intervention-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `performedAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp (e.g. `2024-06-01T10:00:00Z`). Represents when the intervention was actually performed, which may differ from `recordedAt` (the log time). | REQ-13.005 |
| `content` | JSON (Structured Data) | Yes | Type-specific intervention payload. Contains an `interventionType` field (discriminator for the kind of intervention, e.g. `TREATMENT`, `SHEARING`, `HOOF_TRIMMING`, `CASTRATION`, `WEANING`) along with type-specific data. For `TREATMENT` type, typical fields include `medication`, `dose`, `meatWithdrawalDays`, `milkWithdrawalDays`. The type list is extensible per product evolution (REQ-13.001). | REQ-13.001, REQ-13.005 |

