# Individual — Attributes

> **Note**: This attribute list describes the business object, not a persistence structure or technical implementation. Derived attributes are computed at the application level.

| Attribute | Type | Required | Constraints / Format | Requirement |
|-----------|------|----------|----------------------|-------------|
| `id` | Long | Yes | System-generated, immutable | [REQ-01.001](../../requirements/business/REQ-01.001.md), [BR-001](../../business-rules/BR-001-unique-internal-identifier.md) |
| `officialId` | String | No | Unique; Official Identifier (AMD/TVD/BDTA). Can be deferred. | [REQ-01.002](../../requirements/business/REQ-01.002.md), [REQ-01.003](../../requirements/business/REQ-01.003.md), [BR-002](../../business-rules/BR-002-deferred-official-identifier-assignment.md) |
| `name` | String | No | Display label: name > officialId > generated fallback. | [REQ-02.002](../../requirements/business/REQ-02.002.md) |
| `birthDate` | LocalDate | Yes | Calendar date, no time or timezone. | [REQ-01.004](../../requirements/business/REQ-01.004.md), [BR-003](../../business-rules/BR-003-lifecycle-date-constraints.md) |
| `deathDate` | LocalDate | No | If stillborn, must equal `birthDate`. | [REQ-01.004](../../requirements/business/REQ-01.004.md), [BR-003](../../business-rules/BR-003-lifecycle-date-constraints.md) |
| `stillborn` | Boolean | Yes | If `true`, `deathDate` equals `birthDate`. | [REQ-01.004](../../requirements/business/REQ-01.004.md), [BR-004](../../business-rules/BR-004-stillborn-lifecycle-progression.md) |
| `living` | Boolean | No | Derived from `deathDate`: `null` → alive, present → deceased. | [REQ-01.005](../../requirements/business/REQ-01.005.md), [BR-004](../../business-rules/BR-004-stillborn-lifecycle-progression.md), [ADR-0002](../../architecture/adr/ADR-0002-living-state-derived-from-death-date.md) |
| `sex` | Enum | Yes | Values: `Male`, `Female`. | [REQ-01.005](../../requirements/business/REQ-01.005.md) |
| `colorPattern` | String | No | From configurable list of options. | [REQ-01.007](../../requirements/business/REQ-01.007.md) |
| `baseColor` | String | No | From configurable list. Used for icon generation. | [REQ-09.001](../../requirements/business/REQ-09.001.md) |
| `agoutiPattern` | String | No | From configurable list. Used for icon generation. | [REQ-09.001](../../requirements/business/REQ-09.001.md) |
| `spotting` | String | No | From configurable list. Used for icon generation. | [REQ-09.001](../../requirements/business/REQ-09.001.md) |
| `dilution` | String | No | From configurable list. Used for icon generation. | [REQ-09.001](../../requirements/business/REQ-09.001.md) |
| `belongsToFlock` | Boolean | No | Derived from FLOCK_ENTRY / FLOCK_EXIT observations. `false` for Lineage individuals. | [REQ-01.008](../../requirements/business/REQ-01.008.md), [BR-019](../../business-rules/BR-019-flock-membership-tracking.md) |
| `sireId` | Long | No | Logical FK to Individual with sex=Male. | [REQ-01.005](../../requirements/business/REQ-01.005.md), [REQ-02.001](../../requirements/business/REQ-02.001.md), [BR-005](../../business-rules/BR-005-parentage-role-semantics.md) |
| `damId` | Long | No | Logical FK to Individual with sex=Female. | [REQ-01.005](../../requirements/business/REQ-01.005.md), [REQ-02.001](../../requirements/business/REQ-02.001.md), [BR-005](../../business-rules/BR-005-parentage-role-semantics.md) |

