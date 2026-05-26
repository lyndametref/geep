# PredictedEvent — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. PredictedEvent is a specialization of FutureEvent. Attributes marked as inherited from FutureEvent are documented in full in `future-event-attributes.md`. Subtype-specific attributes are listed below the inherited ones.

## Inherited from FutureEvent (supertype)

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | [REQ-04](../../requirements/business/REQ-04.md), [BR-010](../../business-rules/BR-010-future-event-unique-identifier.md) |
| `individualId` | Long | Yes | Logical FK to Individual. | [REQ-04](../../requirements/business/REQ-04.md), [BR-018](../../business-rules/BR-018-record-ownership.md) |
| `sourceRecordId` | Long | No | Logical FK to the Record that derived this PredictedEvent. | [BR-012](../../business-rules/BR-012-lambing-prediction-based-on-mating-observation.md), [BR-022](../../business-rules/BR-022-lambing-process-flow.md), [BR-014](../../business-rules/BR-014-medication-quarantine-delay.md) |
| `createdAt` | Timestamp (UTC) | Yes | ISO-8601 UTC timestamp. When the PredictedEvent was derived or manually created. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-05.002](../../requirements/business/REQ-05.002.md) |
| `futureEventType` | Enum | Yes | Fixed to `PREDICTED_EVENT` for PredictedEvent instances. | [REQ-04](../../requirements/business/REQ-04.md) |

## PredictedEvent-specific attributes

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `status` | Enum (PredictionStatus) | Yes | Lifecycle status. Values: `PENDING` (awaiting realization), `REALIZED` (occurred, recorded via Observation), `ABORTED` (will not occur). | [REQ-04](../../requirements/business/REQ-04.md), [REQ-05](../../requirements/business/REQ-05.md), [BR-015](../../business-rules/BR-015-future-event-realization.md) |
| `earliestDate` | Timestamp (UTC) | Yes | Earliest expected date for the probabilistic outcome (e.g., earliest lambing date = mating date + 140 days). | [REQ-04.004](../../requirements/business/REQ-04.004.md), [BR-012](../../business-rules/BR-012-lambing-prediction-based-on-mating-observation.md) |
| `latestDate` | Timestamp (UTC) | Yes | Latest expected date for the probabilistic outcome. Must be >= `earliestDate`. | [REQ-04.004](../../requirements/business/REQ-04.004.md), [BR-012](../../business-rules/BR-012-lambing-prediction-based-on-mating-observation.md) |
| `content` | JSON (Structured Data) | No | Type-specific prediction payload. Contains an `predictionType` field (discriminator for the kind of prediction, e.g. `BIRTH`, `HEAT`) along with type-specific derived data. | [REQ-04](../../requirements/business/REQ-04.md), [REQ-04.004](../../requirements/business/REQ-04.004.md) |
