package dev.wahid.bare

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import androidx.tv.material3.Text

private const val HiddenAlpha = 0.35f

@Composable
fun AllAppsScreen(
    apps: List<AppEntry>,
    hiddenApps: List<AppEntry>,
    onOpen: (AppEntry) -> Unit,
    onHide: (AppEntry) -> Unit,
    onUnhide: (AppEntry) -> Unit,
    onDismiss: () -> Unit,
) {
    val allApps = remember(apps, hiddenApps) { (apps + hiddenApps).sortedBy { it.label.lowercase() } }
    val hiddenIds = remember(hiddenApps) { hiddenApps.map { it.id }.toSet() }
    val firstApp = remember { FocusRequester() }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(
            colors = SurfaceDefaults.colors(containerColor = Color.Black, contentColor = Color.White),
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(modifier = Modifier.padding(start = 48.dp, end = 48.dp, top = 40.dp)) {
                Text(text = "All apps", style = MaterialTheme.typography.displaySmall)
                Text(
                    text = "OK opens an app. Hold OK to hide or show it.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 24.dp),
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 120.dp),
                    contentPadding = PaddingValues(vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    itemsIndexed(allApps, key = { _, app -> app.id }) { index, app ->
                        val hidden = app.id in hiddenIds
                        AppTile(
                            app = app,
                            hidden = hidden,
                            onClick = { onOpen(app) },
                            onLongClick = { if (hidden) onUnhide(app) else onHide(app) },
                            modifier = if (index == 0) Modifier.focusRequester(firstApp) else Modifier,
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) { firstApp.requestFocus() }
}

@Composable
private fun AppTile(
    app: AppEntry,
    hidden: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val alpha = if (hidden) HiddenAlpha else 1f

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AppCircle(onClick = onClick, onLongClick = onLongClick, modifier = modifier) {
            app.icon?.let {
                Image(
                    bitmap = it,
                    contentDescription = app.label,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().alpha(alpha),
                )
            }
        }
        Text(
            text = app.label,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).alpha(alpha),
        )
        Text(
            text = "Hidden",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.alpha(if (hidden) 1f else 0f),
        )
    }
}
