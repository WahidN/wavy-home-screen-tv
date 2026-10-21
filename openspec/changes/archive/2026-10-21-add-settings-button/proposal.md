lease ## Why

With Bare as the home app, the cog key on the TCL remote does nothing, so the TV settings are hard to reach. A cog button on the home screen and a working cog key make it one press again.

## What Changes

- A small cog icon button at the top right of the home screen, right of the PlayStation button.
- Icon only, no text. Screen readers hear "Settings".
- Pressing OK on it opens the full TV settings.
- Always shown, also when the TV has no HDMI 1.
- Reach it with the remote like the PlayStation button. It does not take focus when Bare opens.
- The cog key on the TCL remote also opens the TV settings while Bare is in front.
- Out of scope: TCL's picture/sound quick menu, deep links into one settings page.

## Capabilities

### New Capabilities
- `settings-shortcut`: a button and the remote's cog key, both open the TV settings.

### Modified Capabilities
- None. The PlayStation button keeps its behavior.

## Impact

- Code: new button in the home header (`HomeScreen.kt`), a launch helper and a key handler in `MainActivity`, one new vector drawable for the cog.
- No new dependency, no manifest change, no new permissions.
- Stacked on `add-close-app` (PR #2), which also edits `HomeScreen.kt` and `MainActivity`.
