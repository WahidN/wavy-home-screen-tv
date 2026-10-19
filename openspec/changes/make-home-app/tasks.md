## 1. App

- [x] 1.1 Add the Home intent filter and `singleTask` to `MainActivity`; verify Bare is listed by `cmd package query-activities -c android.intent.category.HOME`
- [x] 1.2 Add a `BackHandler` on the home screen; verify on the TV that Back keeps Bare on screen

## 2. Make it the home screen

- [x] 2.1 Try `pm set-home-activity`; verify with the Home key on the TV, else use the disable fallback, and note the result in design.md
- [x] 2.2 Write `scripts/set-home.sh` (with the installed check) and `scripts/restore-home.sh`; verify restore brings Google TV back and set makes Bare home again

## 3. Check

- [x] 3.1 Run `./gradlew lint assembleDebug testDebugUnitTest` and verify no errors
- [ ] 3.2 Restart the TV and verify Bare shows (only with the user's OK, it interrupts the TV)
