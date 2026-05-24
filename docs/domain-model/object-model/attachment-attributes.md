# Attachment — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Attachment is a journal entry attachment (photo, PDF, video) linked to a Record. It is not a specialization of any supertype.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable. | BR-009 |
| `recordId` | Long | Yes | FK to the parent Record. Mandatory and immutable once set. | BR-009, BR-016 |
| `attachmentType` | Enum | Yes | Allowed values: `PHOTO`, `DOCUMENT`. Discriminator for the media type. | REQ-04.006, BR-008 |
| `uri` | String | Yes | Content URI or file path. Must be a valid, non-empty URI. | BR-008 |
| `importedAt` | Timestamp (UTC) | No | ISO-8601 UTC timestamp of when the media was originally captured (e.g. `2024-06-01T10:00:00Z`). Nullable if capture time is unknown. | REQ-04.006 |
