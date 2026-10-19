## Purpose

Makes Bare the TV's home screen, so it shows on Home and when the TV turns on, and lets the user go back to the stock home screen.

## ADDED Requirements

### Requirement: Bare is the home screen
After running the set-home script, pressing Home SHALL show Bare. Turning on the TV SHALL show Bare when the TV starts on its home screen.

#### Scenario: Home key
- **WHEN** the user presses Home in any app
- **THEN** Bare's home screen shows

#### Scenario: TV restart
- **WHEN** the TV restarts
- **THEN** Bare's home screen shows, not Google TV's

### Requirement: Back stays on home
Pressing Back on Bare's home screen, with no menu or screen open, SHALL do nothing.

#### Scenario: Back on home
- **WHEN** the user presses Back on the home screen
- **THEN** Bare stays on screen

### Requirement: Safe setup and undo
The set-home script MUST refuse to change anything when Bare is not installed. The restore script SHALL bring back Google TV's home screen.

#### Scenario: Bare missing
- **WHEN** the set-home script runs and Bare is not installed
- **THEN** it stops with a message and changes nothing

#### Scenario: Restore
- **WHEN** the user runs the restore script
- **THEN** pressing Home shows Google TV's home screen again
