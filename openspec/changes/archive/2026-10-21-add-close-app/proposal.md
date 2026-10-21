## Why

Apps keep running in the background after you go back home. Sometimes you want an app to start fresh (it hangs, or it reopens on an old screen). Today that means digging through the TV settings to find the app and press "Force stop".

## What Changes

- Holding OK on an app in the home row opens a menu with three buttons: Hide, App info, Cancel. Focus still starts on Cancel.
- The menu title becomes the app name, since it is no longer only about hiding.
- App info opens the system App info page of that app. There the user presses "Force stop" to fully close it.
- Out of scope: the All apps screen stays as it is (holding OK there still flips hidden and shown).

## Capabilities

### New Capabilities
- `close-apps`: reach the system force stop for an app from the hold-OK menu on the home row.

### Modified Capabilities
- `hide-apps`: the hold-OK menu gets a new title and an "App info" button next to Hide.

## Impact

- Code: `AppDialogs.kt` (menu), `HomeScreen.kt` (wire App info), `MainActivity.kt` (open the App info page).
- No new permissions, no network, no new dependency.
