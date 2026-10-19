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

    private val _apps = MutableStateFlow<List<AppEntry>>(emptyList())
    val apps: StateFlow<List<AppEntry>> = _apps

    private val _hdmi1InputId = MutableStateFlow<String?>(null)
    val hdmi1InputId: StateFlow<String?> = _hdmi1InputId

    fun reload() {
        viewModelScope.launch {
            _apps.value = withContext(Dispatchers.IO) { loadTvApps(getApplication()) }
            _hdmi1InputId.value = withContext(Dispatchers.IO) { findHdmi1InputId(getApplication()) }
        }
    }
}
