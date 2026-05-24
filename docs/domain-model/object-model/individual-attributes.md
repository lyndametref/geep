# Individual — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Derived attributes are computed at the application level.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable | REQ-01.001, BR-001 |
| `officialId` | String | No | Unique; Official Identifier (AMD/TVD/BDTA). Can be deferred. | REQ-01.002, REQ-01.003, BR-002 |
| `name` | String | No | Display label: name > officialId > generated fallback. | REQ-02.002 |
| `birthDate` | LocalDate | Yes | Calendar date, no time or timezone. | REQ-01.004, BR-003 |
| `deathDate` | LocalDate | No | If stillborn, must equal `birthDate`. | REQ-01.004, BR-003 |
| `stillborn` | Boolean | Yes | If `true`, `deathDate` equals `birthDate`. | REQ-01.004, BR-004 |
| `living` | Boolean | No | Derived from `deathDate`: `null` → alive, present → deceased. | REQ-01.005, BR-004, ADR-0002 |
| `sex` | Enum | Yes | Values: `Male`, `Female`. | REQ-01.005 |
| `colorPattern` | String | No | From configurable list of options. | REQ-01.007 |
| `baseColor` | String | No | From configurable list. Used for icon generation. | REQ-09.001 |
| `agoutiPattern` | String | No | From configurable list. Used for icon generation. | REQ-09.001 |
| `spotting` | String | No | From configurable list. Used for icon generation. | REQ-09.001 |
| `dilution` | String | No | From configurable list. Used for icon generation. | REQ-09.001 |
| `belongsToFlock` | Boolean | No | Derived from FLOCK_ENTRY / FLOCK_EXIT observations. `false` for Lineage individuals. | REQ-01.008, BR-019 |
| `sireId` | Long | No | Logical FK to Individual with sex=Male. | REQ-01.005, REQ-02.001, BR-005 |
| `damId` | Long | No | Logical FK to Individual with sex=Female. | REQ-01.005, REQ-02.001, BR-005 |

