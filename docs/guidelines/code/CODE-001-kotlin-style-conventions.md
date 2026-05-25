# CODE-001 Kotlin Style & Conventions

**Applies to:** mobile

## Guideline

All Kotlin code must follow the Google Kotlin Style Guide and the project's established conventions.

## Rules

- Use `val` over `var` by default. Use `var` only when state mutation is explicitly required.
- Use `data class` for value objects / DTOs / entities. Use `sealed class` or `sealed interface` for closed type hierarchies (e.g. `Record` in `core-model`).
- Use `enum class` for fixed sets of values (e.g. `Sex`, `PredictionStatus`, `TaskStatus`, `DelayStatus`).
- Use `@JvmStatic` / `@JvmField` only when Java interop is required.
- Prefer extension functions over utility classes with static methods.
- Prefer named arguments for constructors with more than 2 parameters.
- Use Kotlin's null safety (`?` / `!!`) intentionally. A `!!` must always be justified by a comment explaining why null is impossible at that point.
- Follow the existing code layout and formatting in the project (no `.editorconfig` yet — establish one if formatting drifts).
- Method and class doc comments: KDoc (`/** ... */`) for public API. One-line comments (`//`) for internal notes.
