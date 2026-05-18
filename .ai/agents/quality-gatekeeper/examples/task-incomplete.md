> Example of a gatekeeper review where the task is **not ready to close** — acceptance criteria are only partially fulfilled.

## Task Reviewed

**TASK-0035** — M4-LSB: Add input validation to all user-facing forms

## Decision

**Incomplete** — AC #4 and DoD #1, #2 are not met. Needs implementation of inline Compose validation feedback and tests before closing.

## Acceptance Criteria Assessment

| # | Criterion | Status | Evidences/Findings |
|---|-----------|--------|----------|
| 1 | BDTA identifier fields validate against expected format | ✅ Pass | Validates pattern `CH\d{6,8}` in Individual form |
| 2 | Name and text fields enforce max length and reject control characters | ✅ Pass | Both Individual and Genealogy forms enforce 100 char limit |
| 3 | Numeric fields (weight, BCS, dates) reject non-numeric input | ✅ Pass | Weight and BCS fields use numeric keyboard + regex filter |
| 4 | Validation feedback shown inline on the form (Compose) | ❌ Fail | No `isError` state or error text implemented — validation silently drops invalid input |
| 5 | Injection characters are rejected or sanitised before DB write | ⚠️ Inconclusive | Repository layer uses parameterised queries, but no explicit sanitisation confirmed |

## Definition of Done Assessment

| # | Criterion | Status | Evidences/Findings |
|---|-----------|--------|----------|
| 1 | Tests pass | ❌ Fail | No test files found for validation logic |
| 2 | Documentation updated | ❌ Fail | No mention of validation rules in docs |
| 3 | No regressions introduced | ✅ Pass | Existing tests still pass |
