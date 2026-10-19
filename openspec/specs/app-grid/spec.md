# app-grid Specification

## Purpose
Shows every installed TV app in one grid so the user can find and open any app with the remote.

## Requirements

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

### Requirement: Navigate with the remote
The app row SHALL be fully usable with a D-pad. The focused app MUST be clearly visible. When Bare opens, the first app SHALL have focus.

#### Scenario: Move focus
- **WHEN** the user presses left or right on the D-pad
- **THEN** focus moves to the previous or next app and that app is highlighted

#### Scenario: Initial focus
- **WHEN** Bare opens
- **THEN** the first app has focus without any key press

### Requirement: Open an app
Pressing OK on a tile SHALL open that app.

#### Scenario: Open focused app
- **WHEN** the user presses OK on a tile
- **THEN** the matching app opens

### Requirement: Keep the list up to date
The system SHALL reload the app list each time Bare comes back to the foreground.

#### Scenario: New app installed
- **WHEN** the user installs an app and then returns to Bare
- **THEN** the new app appears in the grid

#### Scenario: App removed
- **WHEN** the user uninstalls an app and then returns to Bare
- **THEN** the app is no longer in the grid

### Requirement: No permissions and no network
The app MUST NOT request any permission that gives access to user data, the network or the system, and MUST NOT make network calls. The only allowed entry is AndroidX's internal `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`, which only lets the app talk to itself.

#### Scenario: Manifest check
- **WHEN** the built APK's manifest is inspected
- **THEN** the only `uses-permission` entry is `<applicationId>.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`

### Requirement: Apps in one sideways row
The apps SHALL be shown in a single horizontal row under the header. The row SHALL scroll sideways so the focused app is always fully visible.

#### Scenario: Scroll to the end
- **WHEN** the user keeps pressing right
- **THEN** the row scrolls and every app can be reached

### Requirement: Round app icons
Each app SHALL be shown as a small circle with the app's icon inside. Only the focused app SHALL show its name, under its circle.

#### Scenario: Focused app shows name
- **WHEN** an app gets focus
- **THEN** its circle is highlighted and its name appears under it

#### Scenario: Other apps show no name
- **WHEN** an app does not have focus
- **THEN** only its round icon is shown
