package dev.wahid.bare

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppsViewModel(application: Application) : AndroidViewModel(application) {

    private val hiddenAppsStore = HiddenAppsStore(application)
    private var allApps = emptyList<AppEntry>()

    private val _apps = MutableStateFlow<List<AppEntry>>(emptyList())
    val apps: StateFlow<List<AppEntry>> = _apps

    private val _hiddenApps = MutableStateFlow<List<AppEntry>>(emptyList())
    val hiddenApps: StateFlow<List<AppEntry>> = _hiddenApps

    private val _hdmi1InputId = MutableStateFlow<String?>(null)
    val hdmi1InputId: StateFlow<String?> = _hdmi1InputId

    fun reload() {
        viewModelScope.launch {
            allApps = withContext(Dispatchers.IO) { loadTvApps(getApplication()) }
            showApps(withContext(Dispatchers.IO) { hiddenAppsStore.read() })
            _hdmi1InputId.value = withContext(Dispatchers.IO) { findHdmi1InputId(getApplication()) }
        }
    }

    fun hide(app: AppEntry) = showApps(hiddenAppsStore.hide(app))

    fun unhide(app: AppEntry) = showApps(hiddenAppsStore.unhide(app))

    private fun showApps(hiddenIds: Set<String>) {
        val (visible, hidden) = allApps.splitHidden(hiddenIds)
        _apps.value = visible
        _hiddenApps.value = hidden
    }
}
