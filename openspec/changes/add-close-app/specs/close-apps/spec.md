## Purpose

Lets the user fully close an app from the home row, by taking them straight to the system page where they can force stop it.

## ADDED Requirements

### Requirement: Open App info
The hold-OK menu on the home row SHALL offer "App info". Pressing it SHALL close the menu and open the system App info page of that app, which offers "Force stop". Back on that page SHALL return to the home screen with the app still in the row. Bare MUST NOT need any permission for this.

#### Scenario: Force stop a running app
- **WHEN** the user holds OK on a running app, releases it, moves to "App info", presses OK, and presses "Force stop" on the page that opens
- **THEN** the app's process is no longer running, also when it runs a foreground service

#### Scenario: Back from App info
- **WHEN** the user presses Back on the App info page
- **THEN** the home screen shows with the app still in the row

#### Scenario: All apps screen unchanged
- **WHEN** the user holds OK on an app on the All apps screen
- **THEN** it flips between hidden and shown, with no menu and no "App info" option
