# app-grid Specification

## Purpose
Shows every installed TV app in one grid so the user can find and open any app with the remote.

## Requirements

### Requirement: Show installed TV apps
The system SHALL show every app that has a TV launcher entry, except Bare itself, sorted by app name (case-insensitive).

#### Scenario: Apps are listed
- **WHEN** the user opens Bare
- **THEN** each installed TV app appears once in the grid, in alphabetical order by name

#### Scenario: Bare hides itself
- **WHEN** the user opens Bare
- **THEN** Bare does not appear in its own grid

### Requirement: Show banner or icon
Each app tile SHALL show the app's TV banner. If the app has no banner, the tile SHALL show the app icon and the app name.

#### Scenario: App with banner
- **WHEN** an app provides a TV banner
- **THEN** its tile shows that banner

#### Scenario: App without banner
- **WHEN** an app provides no TV banner
- **THEN** its tile shows the app icon and the app name

### Requirement: Navigate with the remote
The grid SHALL be fully usable with a D-pad. The focused tile MUST be clearly visible. When Bare opens, the first tile SHALL have focus.

#### Scenario: Move focus
- **WHEN** the user presses a direction on the D-pad
- **THEN** focus moves to the next tile in that direction and that tile is highlighted

#### Scenario: Initial focus
- **WHEN** Bare opens
- **THEN** the first tile has focus without any key press

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
