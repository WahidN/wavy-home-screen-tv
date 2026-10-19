package dev.wahid.bare

import android.text.format.DateFormat
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import androidx.tv.material3.Text
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun HomeScreen(
    apps: List<AppEntry>,
    hiddenApps: List<AppEntry>,
    hdmi1InputId: String?,
    onOpen: (AppEntry) -> Unit,
    onHide: (AppEntry) -> Unit,
    onUnhide: (AppEntry) -> Unit,
    onSwitchInput: (String) -> Unit,
) {
    var appToHide by remember { mutableStateOf<AppEntry?>(null) }
    var showAllApps by remember { mutableStateOf(false) }

    BackHandler {}

    Surface(
        colors = SurfaceDefaults.colors(containerColor = Color.Black, contentColor = Color.White),
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth().padding(start = 48.dp, end = 48.dp, top = 40.dp, bottom = 32.dp)) {
                Header(modifier = Modifier.weight(1f))
                if (hdmi1InputId != null) {
                    Button(onClick = { onSwitchInput(hdmi1InputId) }) {
                        Text("PlayStation")
                    }
                }
            }
            AppRow(
                apps = apps,
                onOpen = onOpen,
                onLongPress = { appToHide = it },
                onOpenAllApps = { showAllApps = true },
            )
        }
    }

    appToHide?.let { app ->
        HideAppDialog(
            app = app,
            onHide = {
                onHide(app)
                appToHide = null
            },
            onDismiss = { appToHide = null },
        )
    }

    if (showAllApps) {
        AllAppsScreen(
            apps = apps,
            hiddenApps = hiddenApps,
            onOpen = onOpen,
            onHide = onHide,
            onUnhide = onUnhide,
            onDismiss = { showAllApps = false },
        )
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    val now = rememberMinuteClock()
    val context = LocalContext.current
    val locale = LocalConfiguration.current.locales[0]
    val datePattern = remember(locale) { DateFormat.getBestDateTimePattern(locale, "EEEEdMMMM") }
    val date = DateFormat.format(datePattern, now).toString().replaceFirstChar { it.titlecase(locale) }
    val time = DateFormat.getTimeFormat(context).format(Date(now))

    Column(modifier = modifier) {
        Text(text = "Welcome Wavy", style = MaterialTheme.typography.displayMedium)
        Text(
            text = "$date · $time",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun rememberMinuteClock(): Long {
    var now by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            while (true) {
                now = System.currentTimeMillis()
                delay(60_000 - now % 60_000)
            }
        }
    }
    return now
}
