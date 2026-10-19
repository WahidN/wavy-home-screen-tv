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
    val banner: ImageBitmap?,
    // Only loaded when there is no banner, to keep memory low.
    val icon: ImageBitmap?,
)

fun List<AppEntry>.forGrid(selfPackage: String): List<AppEntry> =
    filter { it.packageName != selfPackage }.sortedBy { it.label.lowercase() }

fun loadTvApps(context: Context): List<AppEntry> {
    val pm = context.packageManager
    val tvLauncherIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)

    return pm.queryIntentActivities(tvLauncherIntent, 0)
        .map { it.activityInfo }
        .map { activity ->
            val banner = activity.loadBanner(pm)?.toBitmap()?.asImageBitmap()
            AppEntry(
                packageName = activity.packageName,
                activityName = activity.name,
                label = activity.loadLabel(pm).toString(),
                banner = banner,
                icon = if (banner == null) activity.loadIcon(pm).toBitmap().asImageBitmap() else null,
            )
        }
        .forGrid(context.packageName)
}
