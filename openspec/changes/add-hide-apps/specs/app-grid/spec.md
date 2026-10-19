## MODIFIED Requirements

### Requirement: Show installed TV apps
The system SHALL show every app that has a TV launcher entry, except Bare itself and apps the user hid, sorted by app name (case-insensitive).

#### Scenario: Apps are listed
- **WHEN** the user opens Bare
- **THEN** each installed TV app that is not hidden appears once in the row, in alphabetical order by name

#### Scenario: Bare hides itself
- **WHEN** the user opens Bare
- **THEN** Bare does not appear in its own row

#### Scenario: Hidden apps are left out
- **WHEN** the user has hidden an app
- **THEN** that app does not appear in the row
