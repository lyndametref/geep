# AGENT.md

## Purpose
This file defines how coding and documentation agents should operate in this repository.

## Single repository
This repository will contain all the applications for the Geep ecosystem as well as the backlog and the requirements.
Here is the structure:
- backlog/ : contain all the backlog items
- docs/ : contain all the documentation and the requirements
- appAndroid/ : android application for mobile phone

## Repository Focus
Geep is planned and documented through requirements and backlog artifacts.
Primary source-of-truth areas:
- `docs/` for domain reference material,requirements and architecture contracts
- `backlog/` for milestones, tasks, decisions, and completion tracking

DO NOT change this folder hierarchy.

## Working Rules For Agents
1. Read before changing
   - Review relevant files in ``docs/`, and `backlog/tasks/` before proposing or applying edits.
2. Explicit assumptions
   - If information is missing, list assumptions instead of inventing silent details.

## Quality Bar
- Always be concise. Use as few words as possible to cover the meaning.
- Clear headings and concise bullets.
- Concrete acceptance criteria where applicable.
- No ambiguous status language (use explicit states like planned, in-progress, blocked, completed).
