# CODE-009 Commit Messages & Change Documentation

**Applies to:** mobile, backend, web

## Guideline

Commit messages must be concise, descriptive, and follow conventional commit format. Changes must be documented in the backlog task.

## Rules

### Format
```
<type>(<scope>): <short summary>

<optional body>
```

- Summary: <= 72 characters, imperative mood, no period.
- Body: wrap at 72 characters, explain what and why (not how).
- Reference backlog task IDs where applicable (e.g. `Refs: TASK-0033`).
- One commit = one logical change. No "fix typo" or "wip" commits on main branches.
- No secrets, no PII, no internal URLs in commit messages.

**Types**: `feat`, `fix`, `refactor`, `test`, `docs`, `style`, `chore`, `perf`.

**Scopes** (examples): `core-model`, `core-database`, `feature-journal`, `app`, `backend-api`, `web-ui`.


### Examples
```
feat(core-model): add WeightObservation to Observation types

fix(core-database): correct ON DELETE CASCADE on ObservationEntity
```

## References

- AGENTS.md §Quality Bar
- Conventional Commits specification
