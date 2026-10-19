## Purpose

Greets the user and shows today's date and the current time at the top of the home screen.

## ADDED Requirements

### Requirement: Greeting title
The home screen SHALL show the title "Welcome Wavy" in large text, aligned to the top left.

#### Scenario: Title visible
- **WHEN** Bare opens
- **THEN** "Welcome Wavy" is shown at the top left in the largest text on the screen

### Requirement: Date and time
Under the title, the home screen SHALL show today's date (weekday, day and month) and the current time, formatted in the TV's language and its 12/24-hour setting.

#### Scenario: Shows today
- **WHEN** Bare opens
- **THEN** the date and time under the title match the TV's clock

#### Scenario: Time keeps up
- **WHEN** Bare stays on screen past the start of a new minute
- **THEN** the shown time updates to the new minute within a few seconds

#### Scenario: Back after a while
- **WHEN** the user returns to Bare after using another app
- **THEN** the date and time are current right away
