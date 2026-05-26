---
id: GEEP-0011
title: M1 Calendar View & Reminders UI
status: To Do
assignee: [@android-dev]
priority: HIGH
milestone: MILESTONE-1
labels: [android, ui, feature-calendar]
dependencies: [GEEP-0001, GEEP-0002, GEEP-0003, GEEP-0004, GEEP-0005, GEEP-0006]
ordinal: 11
---

## Description

Implement the Calendar view in the `:feature:calendar` module. Displays predicted future events (lambing, heat season) and reminders (weaning, hoof trimming, shearing). Supports REQ-05.002, REQ-05.003, and REQ-13.006.

## Acceptance Criteria

<!-- AC:BEGIN -->
- [ ] #1 Calendar screen displays a monthly or agenda view showing predicted events and reminders — REQ-05.002
- [ ] #2 Predicted lambing events (from BR-012) and predicted heat-season events appear on the calendar with date ranges (earliest-latest window) — REQ-05.002
- [ ] #3 Reminders appear on the calendar for weaning (from BR-022), hoof trimming, and shearing — REQ-05.003 and REQ-13.006
- [ ] #4 Tapping a calendar event shows details: affected individual(s), event type, date/window, status (pending/realized/elapsed)
- [ ] #5 Events are color-coded by type (predicted events, reminders, past events)
<!-- AC:END -->

## Definition of Done
<!-- DOD:BEGIN -->
- [ ] #1 Feature module compiles
- [ ] #2 Calendar renders events from mock/demo data
- [ ] #3 Event detail navigation works
<!-- DOD:END -->
