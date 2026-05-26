---
id: GEEP-0002
title: M1 Mobile App Module Architecture
status: To Do
assignee: [@architect]
priority: HIGH
milestone: MILESTONE-1
labels: [android, architecture]
dependencies: [GEEP-0001]
ordinal: 2
---

## Description

Define the mobile app's module boundaries, inter-module contracts, and public API surfaces. Each module's public API is specified so that implementation tasks can proceed in parallel with clear interfaces. This covers `:core:model`, `:core:database`, `:core:rules`, `:feature:individuals`, `:feature:observations`, `:feature:genealogy`, `:feature:calendar`, `:feature:journal`, `:feature:backup` modules.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Each core module (`model`, `database`, `rules`, `security`) has a documented public API surface with interface contracts
- [ ] #2 Each feature module (`individuals`, `observations`, `genealogy`, `calendar`, `journal`, `backup`) has documented public API surfaces (ViewModels, screens, navigators)
- [ ] #3 Inter-module dependency direction is documented and enforces that feature modules depend only on core modules, never on other feature modules
- [ ] #4 A shared navigation contract is defined so feature modules register their routes without cross-feature coupling
- [ ] #5 Module boundaries are reviewed against all M1 REQs to ensure each REQ is fully covered by at least one feature module
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Module architecture document is written to `docs/architecture/mobile-module-architecture.md`
- [ ] #2 All feature-to-core API contracts are documented
- [ ] #3 Reviewer sign-off from @architect
<!-- DOD:END -->
