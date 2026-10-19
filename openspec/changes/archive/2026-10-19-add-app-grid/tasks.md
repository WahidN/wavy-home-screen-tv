## 1. Project setup

- [x] 1.1 Create the Gradle project (Kotlin DSL, version catalog, one `app` module, minSdk 30, Compose + `tv-material`) and verify `./gradlew assembleDebug` succeeds
- [x] 1.2 Write the manifest: TV-only features (`leanback` required, touchscreen not required), app banner, `LEANBACK_LAUNCHER` entry, `<queries>` block, no permissions of our own; verify with `aapt2 dump badging` that the only `uses-permission` is AndroidX's internal one

## 2. App list

- [x] 2.1 Add `AppEntry` and an app loader that queries TV apps, skips Bare, sorts by name, and loads banner or falls back to icon; verify with a unit test for skip + sort
- [x] 2.2 Add a `ViewModel` that loads apps on a background coroutine and reloads on resume; verify by installing a test APK and seeing it appear after returning to Bare

## 3. Grid UI

- [x] 3.1 Build the grid screen with `tv-material` cards (banner tile, icon + name fallback) and first-tile focus; verify on the TV that D-pad moves a visible focus highlight
- [x] 3.2 Open the app on OK; verify on the TV that each tested app opens

## 4. Check

- [x] 4.1 Run `./gradlew lint assembleDebug` and verify no errors
- [x] 4.2 Install on the TV with `adb install`, take a screenshot with `adb exec-out screencap`, and record cold start time from `adb shell am start -W` as the speed baseline

## Speed baseline (TCL Smart TV G07, Android 11, 2026-10-19)

- Cold start, debug build: about 3.3 s (5 runs: 3596, 3269, 3288, 3334, 3378 ms)
- Cold start, release build (no R8, no profile): about 2.9 s (3252, 3049, 2858, 2936, 2859 ms)
- Cold start, release build fully AOT-compiled (`cmd package compile -m speed`): about 0.54 s (524, 530, 514, 577, 550 ms)
- Hot start (back to Bare): 93 ms
