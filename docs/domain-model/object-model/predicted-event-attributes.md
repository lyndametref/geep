# PredictedEvent — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. PredictedEvent is a specialization of FutureEvent. Attributes marked as inherited from FutureEvent are documented in full in `future-event-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from FutureEvent (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | REQ-04, BR-016 |
| `individualId` | Long | Yes | Logical FK to Individual. | REQ-04, BR-018 |
| `sourceRecordId` | Long | No | Logical FK to the Record that derived this PredictedEvent. | BR-012, BR-013, BR-014 |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp. When the PredictedEvent was derived or manually created. | REQ-04, REQ-05.002, BR-016 |
| `futureEventType` | Enum | Yes | Fixed to `PREDICTED_EVENT` for PredictedEvent instances. | BR-010 |

## PredictedEvent-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `status` | Enum (PredictionStatus) | Yes | Lifecycle status. Values: `PENDING` (awaiting realization), `REALIZED` (occurred, recorded via Observation), `ABORTED` (will not occur). | BR-010, BR-015 |
| `earliestDate` | Timestamp (UTC) | Yes | Earliest expected date for the probabilistic outcome (e.g., earliest birth date = mating date + 140 days). | REQ-04.004, BR-012 |
| `latestDate` | Timestamp (UTC) | Yes | Latest expected date for the probabilistic outcome. Must be >= `earliestDate`. | REQ-04.004, BR-012 |
| `content` | JSON (Structured Data) | No | Type-specific prediction payload. Contains an `predictionType` field (discriminator for the kind of prediction, e.g. `BIRTH`, `HEAT`) along with type-specific derived data. | REQ-04, REQ-04.004 |
