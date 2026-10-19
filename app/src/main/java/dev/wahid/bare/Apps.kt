package dev.wahid.bare

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap

data class AppEntry(
    val packageName: String,
    val activityName: String,
    val label: String,
    val icon: ImageBitmap?,
) {
    val id: String get() = "$packageName/$activityName"
}

fun List<AppEntry>.forGrid(selfPackage: String): List<AppEntry> =
    filter { it.packageName != selfPackage }.sortedBy { it.label.lowercase() }

fun List<AppEntry>.splitHidden(hiddenIds: Set<String>): Pair<List<AppEntry>, List<AppEntry>> =
    partition { it.id !in hiddenIds }

fun loadTvApps(context: Context): List<AppEntry> {
    val pm = context.packageManager
    val tvLauncherIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)

    return pm.queryIntentActivities(tvLauncherIntent, 0)
        .map { it.activityInfo }
        .map { activity ->
            AppEntry(
                packageName = activity.packageName,
                activityName = activity.name,
                label = activity.loadLabel(pm).toString(),
                icon = activity.loadIcon(pm).toBitmap().asImageBitmap(),
            )
        }
        .forGrid(context.packageName)
}
