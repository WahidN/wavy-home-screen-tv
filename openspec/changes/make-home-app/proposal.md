## Why

Bare only opens when you start it yourself. When you turn on the TV or press Home, Google TV's home screen still shows. Bare should be the home screen.

## What Changes

- Bare registers as a Home app.
- Pressing Home, or turning on the TV, opens Bare.
- Back on Bare's home screen does nothing (it is the bottom screen).
- Two scripts: `scripts/set-home.sh` makes Bare the home screen, `scripts/restore-home.sh` brings Google TV's home back.
- Out of scope: a Settings button (the remote has one), closing open screens when Home is pressed.

## Capabilities

### New Capabilities
- `home-app`: Bare is the TV's home screen, and that can be undone.

### Modified Capabilities
- None.

## Impact

- Manifest: Home intent filter and launch mode on `MainActivity`.
- New `scripts/` folder with two adb scripts.
- On the TV: Google TV's home screen is set aside (preferred home, or disabled if needed). Undo with `restore-home.sh`.
