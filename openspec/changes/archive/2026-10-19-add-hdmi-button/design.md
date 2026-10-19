## Context

Checked on the TV with adb:
- HDMI 1 is input `com.tcl.tvinput/.passthroughinput.TvPassThroughService/HW1413744128` (hardware HDMI port 1).
- `com.tcl.tv/.TVActivity` handles `ACTION_VIEW` on passthrough input URIs. That is the standard Android way to switch inputs.

## Decisions

**Switch with the standard passthrough intent.**
`Intent(ACTION_VIEW, TvContract.buildChannelUriForPassthroughInput(inputId))`. Works with any TV that follows Android TV's input framework. Alternative: start TCL's TV app with TCL-specific extras. Undocumented and breaks on updates.

**Find HDMI 1 with `TvInputManager`, not a hardcoded id.**
Take inputs of type `TYPE_HDMI` without a parent (skip connected CEC devices), and pick the one labeled "HDMI 1". The id contains a hardware number that can change after a firmware update, so hardcoding it is fragile. Look it up once per resume, it is a fast local call.

**No `<queries>` entry.**
Tested on the TV: `TvInputManager` lists all HDMI inputs without it. Package visibility does not filter this list. No permission needed.

## Risks / Trade-offs

- [The input label is not exactly "HDMI 1" on this TV (e.g. a custom name)] → Checked on the TV: labels are 'HDMI 1', 'HDMI 2', 'HDMI 3', and 'HDMI 1' is hardware port 1. If you rename the input in TV settings, the button hides.
- [TCL's TV app opens its own overlay or menu first] → Check on the TV. Accept if one press still lands on HDMI 1.
