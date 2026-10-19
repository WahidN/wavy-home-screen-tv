package dev.wahid.bare

import android.content.Context
import androidx.core.content.edit

class HiddenAppsStore(context: Context) {

    private val prefs = context.getSharedPreferences("hidden_apps", Context.MODE_PRIVATE)

    fun read(): Set<String> = prefs.getStringSet(KEY, null).orEmpty()

    fun hide(app: AppEntry): Set<String> = save(read() + app.id)

    fun unhide(app: AppEntry): Set<String> = save(read() - app.id)

    private fun save(ids: Set<String>): Set<String> {
        prefs.edit { putStringSet(KEY, ids) }
        return ids
    }

    private companion object {
        const val KEY = "ids"
    }
}
