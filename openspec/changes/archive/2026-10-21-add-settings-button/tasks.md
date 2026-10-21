## 1. Prove the remote key

- [x] 1.1 Log every key `MainActivity` gets in `onKeyDown`, press the cog key on the TV; verify the log shows 4077, then remove the log. If it does not show, stop and report

## 2. Button and key

- [x] 2.1 Add `res/drawable/ic_settings.xml` with Material's cog path; verify it builds
- [x] 2.2 Add `openSettings()` in `MainActivity` that starts `Settings.ACTION_SETTINGS`, pass it to `HomeScreen` as `onOpenSettings`; verify it builds
- [x] 2.3 Add an `IconButton` with the cog (content description "Settings") right of the PlayStation button, always shown; verify with a screenshot on the TV
- [x] 2.4 Add `Modifier.onCogKey` (opens settings on key 4077, passes other keys on) and put it on the root of home, All apps and the app menu; verify it builds

## 3. Check on the TV

- [x] 3.1 OK on the cog button opens the TV settings (`dumpsys activity` shows `MainSettings` on top), Back returns to Bare
- [x] 3.2 Cog key on the remote opens the TV settings from home, from the app menu and from All apps
- [x] 3.3 D-pad: right from PlayStation reaches the cog, down goes back to the app you came from, no focus on open, note where up from the row lands
- [x] 3.4 Run `./gradlew lint assembleDebug testDebugUnitTest` and verify no errors, and `aapt2 dump badging` shows no new permissions

## Results (TCL Smart TV G07, 2026-10-21)

- 1.1: on home the cog key reaches `MainActivity.onKeyDown` as 4077. Inside All apps (a Compose `Dialog`, its own window) TCL logs 4077 but `onKeyDown` does not get it. Log removed.
- 2.3: cog at the top right, right of PlayStation, same top and bottom (y 72 to 168 px). Focused it turns white like PlayStation.
- 3.1: OK on the cog opens `com.android.tv.settings/.MainSettings`, Back returns to Bare with focus on the cog.
- 3.2: key 4077 sent with `adb shell input keyevent 4077` opens `MainSettings` from home, All apps and the app menu. Back returns to the same screen (All apps and the menu stay open). The real remote press was checked on home in 1.1.
- 3.3: no focus on the cog on open (Nuvio has it). Up from Nuvio lands on PlayStation, right reaches the cog, down returns to Nuvio.
- 3.4: lint 0 errors (1 old warning in `banner.xml`), build and tests pass. `aapt2` shows only the androidx internal permission.
