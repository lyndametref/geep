# CODE-011 Code Review Standards

**Applies to:** mobile, backend, web

## Guideline

Every code change must be reviewed before merging. Reviews must focus on correctness, security, maintainability, and alignment with the domain model.

- Review must check:
  - Does the change align with the domain model and business rules?
  - Are there security concerns (hardcoded secrets, injection vectors, PII exposure)?
  - Are tests included and meaningful?
  - Does the code follow project conventions (naming, structure, null safety)?
  - Are timestamps handled with timezone?
  - Are all new strings user-facing? If so, are they externalized for localization?

- No merge without passing CI (lint, tests, SAST, dependency scan).
- No new warnings introduced.
 