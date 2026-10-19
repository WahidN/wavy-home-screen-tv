#!/bin/sh
# Zet de Google TV home en setupwraith weer aan.
set -e
ADB="${ADB:-$HOME/Library/Android/sdk/platform-tools/adb}"

"$ADB" shell pm enable com.google.android.tungsten.setupwraith
"$ADB" shell pm enable com.google.android.apps.tv.launcherx
"$ADB" shell cmd package resolve-activity --brief -a android.intent.action.MAIN -c android.intent.category.HOME | tail -1
