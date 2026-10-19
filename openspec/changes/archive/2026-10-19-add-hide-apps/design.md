## Context

Builds on `redesign-home` (row of round icons, `tv-material` `Surface`). Hidden state is the first thing Bare stores.

## Decisions

**Store hidden apps in `SharedPreferences` as a string set.**
Key per app: `packageName/activityName`. It is one small set, read on the loader's background thread. DataStore is Google's newer choice, but it adds a dependency and a Flow for no gain at this size. Easy to move later if we store more.

**Hold OK = long click.**
`tv-material` `Surface` has `onLongClick`. Holding OK on the remote sends repeat key events, which Compose turns into a long click. Alternative: a separate edit mode. More UI for the same result.

**Hide menu as a `Dialog`, focus on Cancel, ignore the held key.**
A small dialog with "Hide" and "Cancel". Back closes it. Dialogs trap focus, so the D-pad cannot wander behind it.
The dialog opens while OK is still held. The repeats and the release of that key would land on the focused button and press it. So the dialog swallows key events until it sees a fresh key press (`repeatCount == 0`). Focus starts on Cancel, the safe choice.

**All apps screen as a full-screen `Dialog`, not a second Activity or a navigation library.**
It covers the whole screen, so it looks like a new screen. The home screen stays alive behind it, so Back lands on the "All apps" circle again with no extra focus code. A second Activity or Navigation Compose would add startup work and code for one screen.

**Filter in the loader, keep the full list too.**
The loader returns all apps. The ViewModel splits them into visible and hidden using the stored set. The All apps screen needs the hidden ones with their icons.

## Risks / Trade-offs

- [The TCL remote does not send repeat events on a held OK, so no long click] → Check on the TV in task 2.1 before building the dialogs. Fallback: the Menu key on the remote opens the same menu.
- [A hidden app id changes after an app update (new launcher activity)] → Rare. The app then shows again and can be hidden again.
