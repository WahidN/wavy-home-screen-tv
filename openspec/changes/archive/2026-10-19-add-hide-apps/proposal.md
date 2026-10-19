## Why

The row shows every TV app, including TCL system apps you never open (Safety Guard, MagiConnect, T-solo). Hiding them keeps the row short and quick to scroll.

## What Changes

- Hold OK on an app: a small menu asks "Hide <app name>?", with focus on Cancel.
- A hidden app disappears from the row. It is **not** removed or disabled on the TV, only hidden in Bare.
- The row always ends with an "All apps" circle. It opens a full screen with all apps in a grid. Hidden apps are dimmed. OK opens an app, holding OK flips it between hidden and shown. Back returns home.
- Hidden apps are remembered after a restart of Bare or the TV.
- Out of scope: reorder, favorites, hiding Bare's own buttons.

## Capabilities

### New Capabilities
- `hide-apps`: hide apps from the row and manage them on an "All apps" screen.

### Modified Capabilities
- `app-grid`: the row leaves out apps the user hid.

## Impact

- Code: hide menu, "All apps" circle and screen in the UI, a small store for hidden app ids, `Apps.kt` filters hidden apps.
- No new permissions, no network. No new dependency (uses Android's built-in `SharedPreferences`).
- Depends on `redesign-home` (the row of round icons).
