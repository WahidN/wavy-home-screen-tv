## Context

Home candidates on the TV (checked with adb), by priority:
- `launcherx` (Google TV home): 2
- `setupwraith/.RecoveryActivity`: 1
- `tv.settings/.system.FallbackHome`: -1000, only during boot

Android caps intent filter priority at 0 for normal apps, so Bare cannot outrank them by priority.

## Decisions

**First try: preferred home with `pm set-home-activity`.**
Android 11 can set a preferred Home activity. Nothing gets disabled, and undo is setting `launcherx` back. Risk: Google TV may ignore or reset it.

**Fallback: disable `launcherx` and `setupwraith`.**
With only `launcherx` disabled, `setupwraith`'s RecoveryActivity (priority 1) would win over Bare (0). Its name suggests it restores the stock launcher. Disabling both leaves Bare as the only real Home. `setupwraith` is Google TV's setup wizard, only needed for setup and factory reset. Undo: enable both.

Result on the TV (task 2.1): `set-home-activity` returned Success, but Home still opened Google TV. So the fallback is used: `set-home.sh` disables `launcherx` and `setupwraith`. The remote's Settings button still opens TV Settings.

**`launchMode="singleTask"` on `MainActivity`.**
Home must bring back the one Bare screen, not stack a new one on each press.

**Back: `BackHandler` that does nothing on the home screen.**
Without it, Back closes Bare and the system restarts it. Dialogs keep their own Back.

**Scripts use the SDK's adb and pass the environment through.**
`ANDROID_SERIAL` and `ANDROID_ADB_SERVER_PORT` work as usual. The set script checks `pm path dev.wahid.bare` first, so the TV never ends up without a home screen.

## Risks / Trade-offs

- [A system update re-enables Google TV home] → Run `set-home.sh` again.
- [TCL turns on to the last input, not the home screen] → TV setting, out of Bare's hands. Note what the TV does in task 3.2.
- [Bare crashes on start with Google TV disabled] → `restore-home.sh` over adb always works, the TV keeps adb on.
