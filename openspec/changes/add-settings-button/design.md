## Context

Checked on the TV with adb: `Settings.ACTION_SETTINGS` resolves to `com.android.tv.settings/.MainSettings`, the full TV settings. The project has no icon library.

The cog key on the TCL remote sends key code 4077, a TCL key, not the standard `KEYCODE_SETTINGS` (176). TCL logs it as `keyString=4077 type=IR`. Sending 176 with adb opens settings even with Bare in front, so the system handles 176 but leaves 4077 to the home app. Bare had input focus during both presses, so 4077 most likely reaches Bare (not yet proven).

## Decisions

**Open settings with `Settings.ACTION_SETTINGS`.**
Standard Android, works on any TV. Alternative: TCL's own quick menu. Not a public intent and TCL only.

**Draw the cog with one vector drawable.**
Copy Material's "settings" path into `res/drawable/ic_settings.xml` and show it with `painterResource`. Alternative: add `material-icons-core` or `-extended`. A whole dependency for one icon, and the extended one is big.

**Use tv-material's `IconButton`.**
Same focus and press look as the PlayStation `Button`, round, made for icon-only buttons.

**Catch 4077 with one Compose key handler on each window's root.**
Tested on the TV: `MainActivity.onKeyDown` gets 4077 on home, but not inside All apps or the app menu. Both are Compose `Dialog`s with their own window, and their keys never reach the activity. So one small `Modifier.onCogKey` goes on the root of home, All apps and the app menu. It opens settings for 4077 and passes every other key on. Alternative: `onKeyDown` for home plus the modifier in the dialogs. Two ways to do one thing.

## Risks / Trade-offs

- [Other TCL remotes use another code for the cog] → Only this remote is in scope. 4077 is not a standard Android key, so catching it does not clash with anything.

- [Up from the row lands on the cog instead of PlayStation] → Compose picks the nearest button above. PlayStation is closer to most apps, so check on the TV and accept either.
