## Why

The banner grid works, but you want a calmer home screen that feels like the stock one: a personal greeting, the date and time, and your apps as small round icons in one row.

## What Changes

- New header at the top left: a big title "Welcome Wavy", with today's date and the time under it.
- The time stays correct while Bare is on screen.
- Apps move from a banner grid to **one sideways row of small round icons**, under the header (like "Je apps" on the stock home).
- The name of the focused app shows under its circle. Other circles show no name.
- Banners are no longer used.
- Out of scope: the HDMI 1 button (separate change `add-hdmi-button`), background images, recommendations.

## Capabilities

### New Capabilities
- `home-header`: greeting title plus live date and time at the top of the home screen.

### Modified Capabilities
- `app-grid`: apps show as round icons in one sideways row instead of a banner grid.

## Impact

- Code: `AppGrid.kt` replaced by a home screen with header + app row, `Apps.kt` loads icons instead of banners, `MainActivity.kt` shows the new screen.
- No new dependencies, no new permissions.
- Assumption: the date follows the TV's language (Dutch on your TV, e.g. "maandag 19 oktober"), and the time follows the TV's 24-hour setting. The title stays English as you wrote it.
