# Business Rules overview

- Identifier rule: Every Individual has a mandatory unique internal identifier.
- Deferred BDTA rule: BDTA association may be omitted initially and assigned later.
- Lifecycle completeness rule: birthDate and deathDate are optional but captured when known; stillborn has same birth and death date.
- Stillborn progression rule: stillborn individuals support identity and lineage capture but do not progress through post-birth observation lifecycle.
- Parentage policy (resolved): Parentage is represented as child-to-parent relationships between individuals with sire/dam role semantics; MVP does not enforce additional cardinality constraints beyond role semantics.
- Batch entry policy (resolved): batch entry is a UI workflow concern; the domain model persists only resulting observations linked to the affected individuals.
- Quarantine representation policy (resolved): treatment quarantine is modeled as two independent optional periods, one for meat and one for milk.
- Visual representation policy (resolved): individual visual representation is a UI concern and is not represented as a domain entity in this model.
- Journal specialization rule: Observation, Treatment, and MedicalAnalysisResult are specialized forms of JournalEntry.
- Future-event specialization rule: PredictedEvent, PlannedTask, and WaitingDelay are specialized forms of FutureEvent.
- Observation scope rule: observation entries can be associated with one or many individuals.
- Derivation timing rule: mating derives a birth PredictedEvent in a 140 to 150 day window after observation.
- Birth follow-up rule: confirmed birth derives a weaning PlannedTask around 3 months later and supports lamb-record creation proposal behavior.
- Delay rule: treatment can derive quarantine WaitingDelay entries that elapse at calculated dates.
- Future-event realization rule: when a FutureEvent happens, realization is captured by creating a new Observation entry.
- Journal rule: individual journals are chronological and include observation entries, future-event entries, treatment details, quarantine delays, attachment references, and medical analysis results.
- Placeholder capability rule: trait assessment is explicit in the model while deduction algorithms remain out of scope.