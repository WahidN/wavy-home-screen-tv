## ADDED Requirements

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

## MODIFIED Requirements

### Requirement: Navigate with the remote
The app row SHALL be fully usable with a D-pad. The focused app MUST be clearly visible. When Bare opens, the first app SHALL have focus.

#### Scenario: Move focus
- **WHEN** the user presses left or right on the D-pad
- **THEN** focus moves to the previous or next app and that app is highlighted

#### Scenario: Initial focus
- **WHEN** Bare opens
- **THEN** the first app has focus without any key press

## REMOVED Requirements

### Requirement: Show banner or icon
**Reason**: Replaced by round app icons.
**Migration**: None. Apps without a banner were already shown with their icon.
