## 1. Storage and filtering

- [x] 1.1 Add a hidden-apps store on `SharedPreferences` (hide, unhide, read set); verify on the TV in 3.1 (`SharedPreferences` needs Android, no plain unit test)
- [x] 1.2 Split apps into visible and hidden in the ViewModel; verify with a unit test that hidden apps are left out and stay sorted

## 2. UI

- [x] 2.1 Hook `onLongClick` on the app circle; verify on the TV that a long press on OK opens the hide dialog
- [x] 2.2 Add the "Hide <app>?" dialog; verify on the TV that hiding removes the app and focus lands on a neighbor
- [x] 2.3 Add the "Hidden apps" item at the end of the row and its dialog to show apps again; verify on the TV that unhiding puts the app back in its sorted place (replaced by 2.4 on request)
- [x] 2.4 Replace the hidden apps popup with an always-present "All apps" circle that opens a full-screen grid of all apps (hidden dimmed, OK flips hidden/shown, Back returns to the circle); verify on the TV with screenshots
- [x] 2.5 On request: OK on the All apps screen opens the app, holding OK flips hidden/shown; hide menu focuses Cancel and ignores the held OK; verify on the TV

## 3. Check

- [x] 3.1 Restart the TV (or force-stop Bare) and verify hidden apps stay hidden
- [x] 3.2 Run `./gradlew lint assembleDebug testDebugUnitTest` and verify no errors, and `aapt2 dump badging` shows no new permissions

## Results (TCL Smart TV G07, 2026-10-19)

- Hold OK opens "Hide <app>?" with Hide focused. Back cancels. Tested with `adb input keyevent --longpress` and by Wahid with the real remote (14 apps hidden).
- Focus after hiding: Compose itself jumps to the first app when the focused app disappears (proven with a temporary log). Fixed by remembering the long-pressed app and focusing its spot once it is gone. Middle and last app checked.
- Hidden apps survive a force-stop of Bare (`shared_prefs/hidden_apps.xml` checked).
- Hidden apps popup worked (stays open while apps remain), then replaced by the All apps screen on request.
- Not verified on the TV: "uninstalled and reinstalled stays hidden". The test mixed with live use of the TV. The stored ids are never removed on uninstall, so it should hold.
- All apps screen (OK opens, hold flips), Cancel focus and ignoring the held OK: tested by Wahid with the real remote.
