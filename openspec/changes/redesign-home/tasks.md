## 1. Header

- [x] 1.1 Add the header with "Welcome Wavy" and a date + time line that ticks each minute while resumed; verify on the TV that it matches the TV clock and changes at the next minute

## 2. App row

- [x] 2.1 Load icons instead of banners in `Apps.kt` and update `AppEntry` + unit tests; verify `./gradlew testDebugUnitTest` passes
- [x] 2.2 Replace the grid with a `LazyRow` of round icons, name only under the focused one; verify on the TV with a screenshot
- [x] 2.3 Keep first-app focus on open and left/right navigation; verify on the TV that right moves one app and the row scrolls to the last app

## 3. Check

- [x] 3.1 Run `./gradlew lint assembleDebug testDebugUnitTest` and verify no errors
- [x] 3.2 Check every app's icon on the TV and list any that look bad
- [x] 3.3 Re-check: an installed app appears after returning, OK opens an app, cold start time compared to the baseline

## Results (TCL Smart TV G07, 2026-10-19)

- Icons: 18 of 19 apps look good. Play Films (`com.google.android.videos`) ships no real icon and shows the default Android robot. No fallback added.
- Cold start, release build: about 3.2 s (3933, 3228, 3163, 3194, 3110 ms), was 2.9 s with the banner grid
- Cold start, release build fully AOT-compiled: about 0.58 s (583, 570, 572, 555, 613 ms), was 0.54 s
