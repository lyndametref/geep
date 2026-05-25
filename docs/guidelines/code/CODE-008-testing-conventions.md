# CODE-008 Testing Conventions

**Applies to:** mobile, backend, web

## Guideline

All code must have automated tests. Follow TDD by default. Each layer has its own testing strategy. 80% is considered a decent test coverage.

### General
- TDD by default: write the test before the implementation.
- All tests must be deterministic: no flaky tests, no network calls in unit tests, no hardcoded time-dependent values.

### Mobile
- **Unit tests**: domain logic, business rules, and utility functions.
- **DAO tests**: Test insert, query, update, delete for each DAO.
- **Migration tests**: verify schema versions 1→N.
- **UI tests**: Compose UI tests.


### Backend
- **Unit tests**: domain and application services.
- **Migration tests**: verify schema versions 1→N.
- **API tests**: verify that API contract is respected

### Web
- **Unit tests**:  components and composables.
- **UI tests**: Compose UI tests.

### Integration
- **Integration tests**: Test transversal use cases across platforme

