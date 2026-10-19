# hdmi-shortcut Specification

## Purpose
Lets the user switch the TV to HDMI 1 with one press from the home screen.

## Requirements

### Requirement: HDMI 1 button
The home screen SHALL show a small button labeled "PlayStation" in the top right corner, when the TV has an HDMI 1 input (the PlayStation is connected there).

#### Scenario: Button visible
- **WHEN** Bare opens on a TV with an HDMI 1 input
- **THEN** a small "PlayStation" button is shown at the top right

#### Scenario: No HDMI 1
- **WHEN** the TV has no HDMI 1 input
- **THEN** no PlayStation button is shown

### Requirement: Switch to HDMI 1
Pressing OK on the button SHALL switch the TV to the HDMI 1 input.

#### Scenario: Switch input
- **WHEN** the user presses OK on the "PlayStation" button
- **THEN** the TV shows the HDMI 1 input

### Requirement: Reach with the remote
The button SHALL be reachable with the D-pad. It MUST NOT take focus when Bare opens.

#### Scenario: Move up to the button
- **WHEN** an app in the row has focus and the user presses up
- **THEN** the "PlayStation" button gets focus

#### Scenario: Back down
- **WHEN** the button has focus and the user presses down
- **THEN** focus moves back to the app row
