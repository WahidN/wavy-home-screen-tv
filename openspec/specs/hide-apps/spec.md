# hide-apps Specification

## Purpose
Lets the user hide apps they never use from the home row, and bring them back, without touching the apps themselves.

## Requirements

### Requirement: Hide an app
Holding OK on an app SHALL open a menu titled with the app name, with the buttons "Hide", "App info" and "Cancel" in that order, and focus on "Cancel". Releasing the held OK MUST NOT press a button in the menu. Confirming "Hide" SHALL remove the app from the row. The app MUST stay installed and usable on the TV.

#### Scenario: Hide
- **WHEN** the user holds OK on an app, releases it, moves to "Hide" and presses OK
- **THEN** the app is no longer in the row and focus moves to a neighbor app

#### Scenario: Release after holding
- **WHEN** the user holds OK on an app and then releases it
- **THEN** the menu stays open with focus on "Cancel" and nothing is hidden and no page opens

#### Scenario: Cancel
- **WHEN** the menu is open and the user presses OK on "Cancel" or presses Back
- **THEN** the menu closes and the app stays in the row

### Requirement: All apps screen
The row SHALL always end with an "All apps" item. Opening it SHALL show a full screen with every TV app in a grid, sorted by name, with hidden apps dimmed and marked "Hidden". Pressing OK on an app there SHALL open it, hidden or not. Holding OK on an app there SHALL flip it between hidden and shown, without a menu. Back SHALL return to the home screen with focus on the "All apps" item.

#### Scenario: Show a hidden app
- **WHEN** the user holds OK on a hidden app on the All apps screen
- **THEN** it is no longer dimmed, and after Back it is in the row at its sorted place

#### Scenario: Hide from the screen
- **WHEN** the user holds OK on a shown app on the All apps screen
- **THEN** it is dimmed and marked "Hidden", and after Back it is no longer in the row

#### Scenario: Open from the screen
- **WHEN** the user presses OK on any app on the All apps screen
- **THEN** that app opens

#### Scenario: Back home
- **WHEN** the user presses Back on the All apps screen
- **THEN** the home screen shows with focus on the "All apps" item

### Requirement: Remember hidden apps
Hidden apps SHALL stay hidden after Bare or the TV restarts.

#### Scenario: After restart
- **WHEN** the user hides an app and the TV restarts
- **THEN** the app is still hidden

#### Scenario: App removed and reinstalled
- **WHEN** a hidden app is uninstalled and later installed again
- **THEN** it stays hidden
