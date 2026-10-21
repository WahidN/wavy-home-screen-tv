## Context

The hold-OK menu is `HideAppDialog` in `AppDialogs.kt`, opened from `HomeScreen`. Actions that need Android (opening an app, switching input) live in `MainActivity` and are passed down as callbacks.

## Decisions

**Open the system App info page instead of closing the app ourselves.**
`Settings.ACTION_APPLICATION_DETAILS_SETTINGS` with `package:<name>` opens the page that has "Force stop". It needs no permission and stops every app, foreground services included.
- Tried first: `ActivityManager.killBackgroundProcesses`. On the TCL TV it logged `Invalid packageName` for Nuvio and Stremio and killed nothing, while the same call from `adb shell am kill` worked. Stremio also runs a foreground service, which that call never kills.
- Not possible: a real force stop from Bare. `FORCE_STOP_PACKAGES` is only granted to system apps.
- Rejected: Shizuku (restart from a Mac after every TV reboot) and an accessibility service that presses "Force stop" (fragile, broad power).

**App info lives in `MainActivity`, like `open` and `switchInput`.**
It starts one intent, so no ViewModel or new file. `HomeScreen` gets an `onAppInfo` callback.

**Rename `HideAppDialog` to `AppMenuDialog`.**
It now does more than hide. It gets an `onAppInfo` callback next to `onHide`. Focus stays on Cancel, and the held-key guard in `DialogCard` stays as is.

## Risks / Trade-offs

- [TCL firmware does not handle the App info intent] → Checked on the TV in task 3.1. Fallback: open the general apps settings list.
- [Two extra clicks compared to a one-press close] → Accepted, it is the only reliable way without system rights.
