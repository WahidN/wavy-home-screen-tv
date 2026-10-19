## Context

New project. Only target device is a TCL TV on Android 11 (API 30), driven by a D-pad remote. TV hardware is often slow (little RAM, weak CPU), so startup and memory matter.

## Goals / Non-Goals

**Goals:**
- Smallest project that meets the spec: one module, one activity, no extra layers.
- Code a React Native developer can read and follow.

**Non-Goals:**
- Being the Home app, clock, tiles, settings storage (later changes).
- Speed tuning with a Baseline Profile (later change). This change only measures a baseline.

## Decisions

**UI: Jetpack Compose for TV, not Leanback or plain Views.**
`androidx.tv:tv-material` gives TV cards with focus scale and highlight out of the box. Leanback is Google's older TV library and only gets maintenance. Plain Views would start a little faster, but need hand-written focus handling. Compose is also closest to React, which you already know.

**minSdk 30 (Android 11).**
The TV is the only target. A higher minSdk means no compatibility code. Trade-off: Bare will not install on TVs older than Android 11.

**Find apps with `queryIntentActivities` + a `<queries>` block.**
Since Android 11, apps can only see other apps they declare interest in. A `<queries>` entry for the `LEANBACK_LAUNCHER` intent is enough to see all TV apps. The alternative, `QUERY_ALL_PACKAGES`, is a permission and sees far more than we need.

**Reload on resume, no package-change listener.**
The list is small (tens of apps) and loading it is cheap. Reloading in `onResume` covers install and uninstall with almost no code. A `LauncherApps` callback can come later if reloading ever feels slow.

**Load banners off the main thread.**
Reading banners from other apps touches disk. Do it on a background coroutine, then show the grid. Keeps the first frame fast.

**State: one `ViewModel`, a `StateFlow<List<AppEntry>>`.**
No DI framework, no navigation library. `AppEntry` holds package name, label, banner or icon, and the launch intent.

**Versions: latest stable at apply time.**
AGP, Kotlin, Compose BOM and `tv-material` get pinned to the newest stable versions when the project is created, in a Gradle version catalog.

## Risks / Trade-offs

- [Compose's first frame is slower than Views on weak TVs] → Measure cold start with `adb shell am start -W` now; fix with a Baseline Profile in a later change.
- [Some TCL system apps have no TV launcher entry, so they won't show] → Accept for now. Settings and Inputs get their own tiles in a later change.
- [Banners are large bitmaps, many apps use memory] → Fine for tens of apps. Revisit if memory use shows up in the speed check.
