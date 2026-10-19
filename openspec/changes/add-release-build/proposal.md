## Why

The TV runs a debug build: larger, slower, and only installable by hand with `apksigner`. A release build is faster and installs with one command.

## What Changes

- Release build turns on R8 (removes unused code) and resource shrinking.
- Release build is signed with the debug key, so it installs as an update over the current app and keeps the hidden apps.
- `./gradlew installRelease` installs it on the TV.
- No behavior change, so no spec change.

## Capabilities

### New Capabilities
- None.

### Modified Capabilities
- None.

## Impact

- `app/build.gradle.kts` release block only.
- A Play Store release would need its own key. Out of scope, Bare is sideloaded.
