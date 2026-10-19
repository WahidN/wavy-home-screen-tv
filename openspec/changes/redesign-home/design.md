## Context

Builds on `add-app-grid`. Looked at the stock Google TV home on the TCL TV: "Je apps" is one row of round icons (about 75dp), no names.

## Decisions

**Icons, not banners.**
Load `loadIcon` for every app and drop banner loading. One small bitmap per app, so less memory than before.

**`LazyRow` with `tv-material` `Surface` in a `CircleShape`.**
`Surface` gives focus scale and a focus border, like `Card` did. `LazyRow` only draws the circles on screen and scrolls to keep focus visible.

**Clock: tick on the minute, only while visible.**
A coroutine sleeps until the next full minute, then updates. It runs with `repeatOnLifecycle(RESUMED)`, so it stops when Bare is hidden and refreshes right away when you come back. Alternative: the system `TIME_TICK` broadcast. Needs a broadcast receiver, more code for the same result.

**Formatting from the system.**
Date pattern from `DateFormat.getBestDateTimePattern(locale, "EEEEdMMMM")`, time from `DateFormat.getTimeFormat(context)`. This follows the TV's language and 24-hour setting with no settings of our own.

## Risks / Trade-offs

- [Some TV apps only ship a banner and a poor icon (blurry or the default robot)] → Check every app on the TV during apply. If some look bad, raise it before adding a fallback.
- [Title in English, date in Dutch] → Accepted as written. Easy to change later.
