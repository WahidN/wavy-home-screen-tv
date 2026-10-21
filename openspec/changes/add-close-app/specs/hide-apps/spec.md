## MODIFIED Requirements

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
