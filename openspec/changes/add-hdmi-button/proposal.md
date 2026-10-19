## Why

You often switch to HDMI 1, where the PlayStation is connected. Today that takes several steps through the TV's input menu. One button on the home screen makes it a single press.

## What Changes

- A small "PlayStation" button at the top right of the home screen.
- Pressing OK on it switches the TV to HDMI 1.
- Reach it with the remote by pressing up from the app row.
- If the TV has no HDMI 1 input, the button is hidden.
- Out of scope: other inputs, an inputs menu, renaming inputs.

## Capabilities

### New Capabilities
- `hdmi-shortcut`: one button that switches the TV to HDMI 1.

### Modified Capabilities
- None.

## Impact

- Code: new button in the home header, a small helper that finds the HDMI 1 input.
- No manifest change, no new permissions.
- Depends on `redesign-home` (the button sits in its header).
