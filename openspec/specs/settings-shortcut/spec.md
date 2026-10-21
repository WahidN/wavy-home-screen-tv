# settings-shortcut Specification

## Purpose
Lets the user open the TV settings with one press from the home screen, with a button or the remote's cog key.

## Requirements

### Requirement: Settings button
The home screen SHALL show a small button with only a cog icon in the top right corner, right of the PlayStation button. The button MUST have the accessible name "Settings". It SHALL be shown whether or not the TV has an HDMI 1 input.

#### Scenario: Button visible
- **WHEN** Bare opens on a TV with an HDMI 1 input
- **THEN** a cog button is shown at the top right, right of the "PlayStation" button

#### Scenario: No HDMI 1
- **WHEN** the TV has no HDMI 1 input
- **THEN** the cog button is still shown at the top right

#### Scenario: Screen reader
- **WHEN** a screen reader is on and the cog button gets focus
- **THEN** it reads "Settings"

### Requirement: Open TV settings
Pressing OK on the button SHALL open the TV's main settings screen. Pressing Back there SHALL return to Bare.

#### Scenario: Open settings
- **WHEN** the user presses OK on the cog button
- **THEN** the TV's main settings screen opens

#### Scenario: Back home
- **WHEN** the settings screen is open and the user presses Back
- **THEN** Bare is shown again

### Requirement: Reach with the remote
The button SHALL be reachable with the D-pad. It MUST NOT take focus when Bare opens.

#### Scenario: Right from PlayStation
- **WHEN** the "PlayStation" button has focus and the user presses right
- **THEN** the cog button gets focus

#### Scenario: Back down
- **WHEN** the cog button has focus and the user presses down
- **THEN** focus moves back to the app row

#### Scenario: Not focused on open
- **WHEN** Bare opens
- **THEN** the cog button does not have focus

### Requirement: Remote cog key
While Bare is in front, pressing the cog key on the TCL remote (key code 4077) SHALL open the TV's main settings screen.

#### Scenario: Cog key on home
- **WHEN** Bare is in front and the user presses the cog key on the remote
- **THEN** the TV's main settings screen opens

#### Scenario: Cog key with a dialog open
- **WHEN** the app menu or the All apps screen is open and the user presses the cog key
- **THEN** the TV's main settings screen opens
