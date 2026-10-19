#!/bin/sh
# Maakt Bare het startscherm: zet de Google TV home en setupwraith uit.
# setupwraith moet ook uit, anders wint zijn RecoveryActivity (prioriteit 1) van Bare (0).
set -e
ADB="${ADB:-$HOME/Library/Android/sdk/platform-tools/adb}"

if ! "$ADB" shell pm path dev.wahid.bare | grep -q "package:"; then
    echo "Bare is not installed on the TV. Nothing changed." >&2
    exit 1
fi

"$ADB" shell pm disable-user --user 0 com.google.android.apps.tv.launcherx
"$ADB" shell pm disable-user --user 0 com.google.android.tungsten.setupwraith
"$ADB" shell cmd package resolve-activity --brief -a android.intent.action.MAIN -c android.intent.category.HOME | tail -1
