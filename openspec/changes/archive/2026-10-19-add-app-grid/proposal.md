## Why

The stock TV home screen is slow and full of ads and recommendations. Bare starts with the one thing a home screen must do: show your apps and open them, fast.

## What Changes

- New Android TV app project (Kotlin, Jetpack Compose for TV), single screen.
- The screen shows a grid of all installed TV apps, with their banner or icon.
- Move with the D-pad, press OK to open an app.
- The list refreshes when you come back to Bare, so new or removed apps show up.
- No permissions, no network, no background work.
- Out of scope for now: being the real Home app (next change), clock, Settings and Inputs tiles, reorder, favorites, hide.

## Capabilities

### New Capabilities
- `app-grid`: list installed TV apps in a grid and open them with the remote.

### Modified Capabilities
- None.

## Impact

- New repo content: Gradle project, one app module, one activity.
- Dependencies: AndroidX core, Activity Compose, Compose for TV (`androidx.tv:tv-material`), Kotlin coroutines.
- Installs next to the stock launcher. Nothing on the TV is disabled in this change.
