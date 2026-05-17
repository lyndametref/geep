---
id: TASK-0005
title: M4 Local Security Baseline
status: Done
assignee:
  - '@agent-k'
created_date: '2026-05-08 14:35'
updated_date: '2026-05-17 19:46'
labels:
  - mobile
  - security
milestone: Iteration 1 Mobile-Only MVP
dependencies:
  - TASK-0002
references:
  - docs/architecture/TECHNICAL_SPECIFICATIONS.md
  - docs/ARCHITECTURE.md
modified_files:
  - docs/MOBILE_SECURITY_POLICY.md
priority: high
ordinal: 1000
---

## Description

<!-- SECTION:DESCRIPTION:BEGIN -->
Produce the Mobile Application Security Policy document (docs/MOBILE_SECURITY_POLICY.md) for the local-only offline mobile MVP. The policy shall identify which security domains from NFR-010 through NFR-014 in docs/architecture/TECHNICAL_SPECIFICATIONS.md apply to an offline-first Android app (no backend, no Google services, Room DB local persistence) and define the applicable controls for each domain. This task is documentation-only. If the analysis identifies security measures that require implementation, follow-up tasks must be created as part of this ticket's output.
<!-- SECTION:DESCRIPTION:END -->

## Acceptance Criteria
<!-- AC:BEGIN -->
- [x] #1 It is decided which security measure are implemented in MVP
- [x] #2 Mobile application security policy is documented
- [x] #3 Identify and document which NFR-010–014 security domains apply to the local-only offline mobile MVP, with rationale for each inclusion or deferral
- [x] #4 Define specific mobile-relevant controls for each applicable domain (e.g. Room DB encryption, SharedPreferences encryption, input validation, no hardcoded secrets, ProGuard/R8 obfuscation, debug-build protections, secure random ID generation, file attachment encryption at rest, biometric/app-lock)
- [x] #5 Document the policy in docs/MOBILE_SECURITY_POLICY.md
- [x] #6 If any domain requires implementation beyond documentation, create backlog tasks for those with clear acceptance criteria and dependency on this ticket
<!-- AC:END -->

## Implementation Plan

<!-- SECTION:PLAN:BEGIN -->
1. Review NFR-010 through NFR-014 in docs/architecture/TECHNICAL_SPECIFICATIONS.md
2. Analyze each NFR against the local-only offline mobile MVP constraints (no backend, no Google services, Room DB)
3. For each applicable domain, define concrete mobile-specific controls
4. Write docs/MOBILE_SECURITY_POLICY.md with rationale, applicable controls, and deferred items
5. Create follow-up implementation tasks for any controls that need code changes
<!-- SECTION:PLAN:END -->

## Implementation Notes

<!-- SECTION:NOTES:BEGIN -->
- Wrote docs/MOBILE_SECURITY_POLICY.md covering all 5 NFR domains
- Zero UX Friction principle documented as core requirement
- 10 implementation-action items identified
- Ready to create follow-up backlog tasks

- Created 6 follow-up implementation tasks: TASK-0033 through TASK-0038

## Actions performed

1. **Reviewed architecture documents**
   - Read docs/architecture/TECHNICAL_SPECIFICATIONS.md — analysed NFR-010 through NFR-014
   - Read docs/architecture/ARCHITECTURE.md — understood project constraints
   - Read appAndroid project structure — identified modules: core-database (Room), core-model, feature-* modules

2. **Asked user questions to calibrate approach**
   - Data sensitivity: confirmed MVP stores both animal data + owner PII
   - Distribution: confirmed side-loaded only (no Play Store)
   - Biometric/app-lock: deferred to skip for MVP
   - Security posture: production-hardened from day one

3. **Proposed and validated the Zero UX Friction strategy**
   - User rejected passphrase approach
   - Proposed Android Keystore-backed transparent encryption (no user interaction)
   - User approved the strategy

4. **Wrote docs/MOBILE_SECURITY_POLICY.md**
   - Defined scope, principles, and Zero UX Friction requirement
   - Mapped each NFR (010-014) with applicability and rationale
   - Defined specific mobile controls per domain:
     - NFR-010: SQLCipher, EncryptedSharedPreferences, EncryptedFile
     - NFR-011: PII minimisation, masking, no telemetry
     - NFR-012: SAST, peer review, ProGuard/R8, signing
     - NFR-013: input validation, no hardcoded secrets, debug-build protection, SecureRandom
     - NFR-014: threat modelling, dependency scanning, vulnerability SLA
   - Documented deferred controls with rationale
   - Listed 10 implementation-action items requiring code changes

5. **Created 6 follow-up backlog tasks**
   - TASK-0033: Keystore-backed data encryption (High)
   - TASK-0034: ProGuard/R8 obfuscation (High)
   - TASK-0035: Input validation on forms (Medium)
   - TASK-0036: Debug-build runtime protection (Medium)
   - TASK-0037: SAST + dependency scanning setup (Medium)
   - TASK-0038: SecureRandom for all IDs (Low)
   - All dependent on TASK-0005
<!-- SECTION:NOTES:END -->

## Final Summary

<!-- SECTION:FINAL_SUMMARY:BEGIN -->
Produced docs/MOBILE_SECURITY_POLICY.md for the local-only offline Android MVP.

**What was done:**
- Analysed NFR-010 through NFR-014 against local-only MVP constraints (no backend, no Google services, Room DB)
- Mapped each NFR as fully applicable, partially applicable, or deferred, with rationale
- Defined concrete Android-specific controls: SQLCipher, EncryptedSharedPreferences, EncryptedFile, ProGuard/R8, input validation, debug-build detection, SecureRandom, SAST scanning
- Established **Zero UX Friction** principle — all encryption is Keystore-backed and transparent to the user
- Created 6 follow-up backlog tasks (TASK-0033–TASK-0038) for implementation items requiring code changes

**Policy covers:**
- Data encryption at rest (Keystore + SQLCipher + EncryptedPrefs + EncryptedFile)
- Privacy by Design (PII minimisation, masking, no analytics)
- SSDF compliance (build automation, peer review, SAST, signing)
- OWASP mobile subset (input validation, no hardcoded secrets, SQLi prevention, debug protection)
- BSA/SAFECode process (threat modelling, dependency scanning, vulnerability SLA)
<!-- SECTION:FINAL_SUMMARY:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [x] #1 Documentation updated
- [x] #2 MOBILE_SECURITY_POLICY.md exists in docs/ and covers all applicable security domains with rationale
- [x] #3 All applicable NFRs from TECHINICAL_SPECIFICATIONS.md are mapped and addressed
- [x] #4 Follow-up implementation tasks created (if applicable)
<!-- DOD:END -->
