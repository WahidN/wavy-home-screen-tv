package dev.wahid.bare

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text

private val CircleSize = 72.dp

@Composable
fun AppRow(
    apps: List<AppEntry>,
    onOpen: (AppEntry) -> Unit,
    onLongPress: (AppEntry) -> Unit,
    onOpenAllApps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequesters = remember { mutableMapOf<String, FocusRequester>() }
    var longPressed by remember { mutableStateOf<Pair<String, Int>?>(null) }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.focusRestorer(),
    ) {
        itemsIndexed(apps, key = { _, app -> app.id }) { index, app ->
            val focusRequester = remember { FocusRequester() }
            DisposableEffect(app.id) {
                focusRequesters[app.id] = focusRequester
                onDispose { focusRequesters.remove(app.id) }
            }
            RowCircle(
                label = app.label,
                onClick = { onOpen(app) },
                onLongClick = {
                    longPressed = app.id to index
                    onLongPress(app)
                },
                modifier = Modifier.focusRequester(focusRequester),
            ) {
                app.icon?.let {
                    Image(bitmap = it, contentDescription = app.label, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                }
            }
        }
        item(key = "all-apps") {
            RowCircle(label = "All apps", onClick = onOpenAllApps) {
                AllAppsIcon()
            }
        }
    }

    LaunchedEffect(apps.isNotEmpty()) {
        if (apps.isNotEmpty()) focusRequesters[apps.first().id]?.requestFocus()
    }

    // Compose zet de focus zelf op de eerste app als de gefocuste app verdwijnt, daarom hier naar de buur.
    LaunchedEffect(apps) {
        val (hiddenId, hiddenIndex) = longPressed ?: return@LaunchedEffect
        if (apps.isNotEmpty() && apps.none { it.id == hiddenId }) {
            longPressed = null
            focusRequesters[apps[hiddenIndex.coerceAtMost(apps.lastIndex)].id]?.requestFocus()
        }
    }
}

@Composable
private fun RowCircle(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(CircleSize)) {
        AppCircle(
            onClick = onClick,
            onLongClick = onLongClick,
            interactionSource = interactionSource,
            modifier = modifier,
            content = content,
        )
        // Unbounded zodat een lange naam de cirkel niet breder maakt en de rij niet verschuift.
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 12.dp)
                .wrapContentWidth(unbounded = true)
                .alpha(if (focused) 1f else 0f),
        )
    }
}

@Composable
fun AppCircle(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        onLongClick = onLongClick,
        shape = ClickableSurfaceDefaults.shape(CircleShape),
        colors = ClickableSurfaceDefaults.colors(containerColor = Color.DarkGray, focusedContainerColor = Color.DarkGray),
        border = ClickableSurfaceDefaults.border(focusedBorder = Border(BorderStroke(3.dp, Color.White))),
        interactionSource = interactionSource,
        modifier = modifier.size(CircleSize),
    ) {
        content()
    }
}

@Composable
private fun AllAppsIcon() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            repeat(2) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    repeat(2) {
                        Box(Modifier.size(12.dp).background(Color.White, RoundedCornerShape(3.dp)))
                    }
                }
            }
        }
    }
}
