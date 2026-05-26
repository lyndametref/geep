# CODE-012 Monorepo Structure

**Applies to:** all

## Guideline

The Geep ecosystem uses a single repository ("monorepo") containing all application source code, shared libraries, documentation, and backlog artifacts. Every component of the ecosystem lives under a single root directory.

## Folder Structure

```
<root>/
  backlog/           # Tasks, project docs, and decisions
  docs/              # Project documentation
    architecture/    # Architecture documentation and ADRs
    business-rules/  # Canonical business rules (BR-XXX)
    domain-model/    # Domain model documentation
    guidelines/      # Coding and documentation guidelines
    requirements/    # Business and non-functional requirements
    specs/           # Technical specifications
  apps/              # Application source code
    appAndroid/      # Android application
    appBackend/      # Backend application
    appWeb/          # Web application
  libs/              # Shared libraries
    libCore/         # Core domain models and shared utilities
    libNetworking/   # Shared networking layer
    libTesting/      # Shared test utilities and fixtures
  .ai/               # AI tooling configuration (canonical home format)
    agents/          # Agent definitions
    skills/          # Skill definitions
```

## Rules

- All apps must reside in their own folder in  `apps/` directory. No application source code outside of it.
- All documentation must reside in the `docs/` directory following the structure above.
- The `backlog/` directory is the source of truth for tasks, project-level decisions, and project-specific docs.
- Shared code must live in `libs/`, not duplicated across apps.
- The `.ai/` directory is the source of truth for AI agent and skill definitions.
- 
