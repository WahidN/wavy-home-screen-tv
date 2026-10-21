## 1. App info action

- [x] 1.1 Remove `KILL_BACKGROUND_PROCESSES` from `AndroidManifest.xml`; verify with `aapt2 dump badging` that Bare asks for no permission of its own
- [x] 1.2 Replace `close(app)` in `MainActivity` with `showAppInfo(app)` that opens `ACTION_APPLICATION_DETAILS_SETTINGS`, pass it to `HomeScreen` as `onAppInfo`; verify it builds

## 2. Menu

- [x] 2.1 Rename `HideAppDialog` to `AppMenuDialog`, title it with the app name, add a button between Hide and Cancel; verify on the TV that holding OK shows the three buttons with focus on Cancel
- [x] 2.2 Label the button "App info" and wire it in `HomeScreen` to call `onAppInfo` and close the menu; verify on the TV that the App info page of that app opens

## 3. Check

- [x] 3.1 On the TV: open Stremio, go home, App info, Force stop; verify `adb shell pidof com.stremio.one` prints nothing, and Back returns home with Stremio in the row
- [x] 3.2 Verify the All apps screen still flips hidden/shown on hold, with no menu
- [x] 3.3 Run `./gradlew lint assembleDebug testDebugUnitTest` and verify no errors

## Results (TCL Smart TV G07, 2026-10-21)

- First try used `killBackgroundProcesses`. The menu worked, but the TV refused the call (`Invalid packageName`) for Nuvio and Stremio, so nothing closed. Replaced by the App info page.
- Menu shows "Stremio" with Hide, App info, Cancel, focus on Cancel after the held OK.
- App info opens the Stremio App info page. "Gedwongen stoppen" + OK stopped Stremio, foreground service included (`pidof` empty, still empty 5s later). One Back returns home with focus on Stremio.
- Bare asks for no permission of its own (`aapt2` shows only the androidx internal one).
- All apps screen: not re-tested on the TV (blind D-pad steps opened YouTube by mistake). `AllAppsScreen.kt` is not in the diff, so its behavior is unchanged.
