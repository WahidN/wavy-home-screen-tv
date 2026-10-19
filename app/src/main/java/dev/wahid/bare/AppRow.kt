package dev.wahid.bare

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
fun AppRow(apps: List<AppEntry>, onOpen: (AppEntry) -> Unit, modifier: Modifier = Modifier) {
    val firstApp = remember { FocusRequester() }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier,
    ) {
        itemsIndexed(apps, key = { _, app -> app.packageName + "/" + app.activityName }) { index, app ->
            AppCircle(
                app = app,
                onClick = { onOpen(app) },
                modifier = if (index == 0) Modifier.focusRequester(firstApp) else Modifier,
            )
        }
    }

    LaunchedEffect(apps.isNotEmpty()) {
        if (apps.isNotEmpty()) firstApp.requestFocus()
    }
}

@Composable
private fun AppCircle(app: AppEntry, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(CircleSize)) {
        Surface(
            onClick = onClick,
            shape = ClickableSurfaceDefaults.shape(CircleShape),
            colors = ClickableSurfaceDefaults.colors(containerColor = Color.DarkGray, focusedContainerColor = Color.DarkGray),
            border = ClickableSurfaceDefaults.border(focusedBorder = Border(BorderStroke(3.dp, Color.White))),
            interactionSource = interactionSource,
            modifier = modifier.size(CircleSize),
        ) {
            app.icon?.let {
                Image(bitmap = it, contentDescription = app.label, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            }
        }
        // Unbounded zodat een lange naam de cirkel niet breder maakt en de rij niet verschuift.
        Text(
            text = app.label,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 12.dp)
                .wrapContentWidth(unbounded = true)
                .alpha(if (focused) 1f else 0f),
        )
    }
}
