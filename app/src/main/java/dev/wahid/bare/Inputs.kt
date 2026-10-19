package dev.wahid.bare

import android.content.Context
import android.media.tv.TvInputInfo
import android.media.tv.TvInputManager

fun findHdmi1InputId(context: Context): String? =
    context.getSystemService(TvInputManager::class.java).tvInputList
        .filter { it.type == TvInputInfo.TYPE_HDMI && it.parentId == null }
        .firstOrNull { it.loadLabel(context).toString() == "HDMI 1" }
        ?.id
