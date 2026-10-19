## 1. Find HDMI 1

- [x] 1.1 Add a helper that returns the HDMI 1 input id (no `<queries>` entry needed, tested); verify on the TV (log) that it returns the port 1 input and what its label is

## 2. Button

- [x] 2.1 Add the small "PlayStation" button (switches to HDMI 1) at the top right of the header, hidden when no input is found; verify with a screenshot on the TV
- [x] 2.2 Switch input on OK; verify on the TV that HDMI 1 is shown
- [x] 2.3 Wire D-pad focus: up from the row reaches the button, down goes back, no focus on open; verify on the TV

## 3. Check

- [x] 3.1 Run `./gradlew lint assembleDebug testDebugUnitTest` and verify no errors, and `aapt2 dump badging` shows no new permissions

## Results (TCL Smart TV G07, 2026-10-19)

- Button label changed to "PlayStation" on request. It still switches to HDMI 1.
- OK on the button: `com.tcl.tv/.TVActivity` opens, `dumpsys tv_input` shows the session on `HW1413744128` (HDMI 1).
- Down from the button first landed on the circle under it. Added `focusRestorer()` on the row, now focus returns to the app you came from.
- Not tested on a device: the "no HDMI 1" case (button hidden). This TV always has HDMI 1.
