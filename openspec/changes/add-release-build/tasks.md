## 1. Release build

- [x] 1.1 Turn on R8 and resource shrinking, sign release with the debug key; verify `./gradlew assembleRelease lint testDebugUnitTest` passes and compare APK size with debug
- [x] 1.2 Install with `./gradlew installRelease` as an update; verify hidden apps are kept and Bare is still home
- [x] 1.3 Check on the TV: header, row, All apps screen and PlayStation button work (R8 must not break anything), and measure cold start against the 3.2 s baseline

## Results (TCL Smart TV G07, 2026-10-19)

- APK size: 24.2 MB debug, 1.6 MB release. Same signing certificate (SHA-256 `b0379a74...`).
- `installRelease` updated the app in place: 15 hidden apps kept, Bare still home.
- R8 broke nothing visible: header, row, All apps screen, PlayStation focus checked.
- Cold start, release + R8: about 0.5 s (662, 432, 485, 470, 452 ms), was about 3.2 s. Likely because release builds use the baseline profiles that ship inside the Compose libraries (debug builds ignore them).
