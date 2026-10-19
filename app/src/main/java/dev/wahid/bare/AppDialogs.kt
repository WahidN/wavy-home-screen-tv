package dev.wahid.bare

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Surface
import androidx.tv.material3.SurfaceDefaults
import androidx.tv.material3.Text

@Composable
fun HideAppDialog(app: AppEntry, onHide: () -> Unit, onDismiss: () -> Unit) {
    val cancelButton = remember { FocusRequester() }

    DialogCard(onDismiss = onDismiss) {
        Text(text = "Hide ${app.label}?", style = MaterialTheme.typography.titleLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 24.dp)) {
            Button(onClick = onHide) { Text("Hide") }
            OutlinedButton(onClick = onDismiss, modifier = Modifier.focusRequester(cancelButton)) { Text("Cancel") }
        }
    }

    LaunchedEffect(Unit) { cancelButton.requestFocus() }
}

@Composable
private fun DialogCard(onDismiss: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    var heldKeyReleased by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            colors = SurfaceDefaults.colors(containerColor = Color(0xFF1C1C1E), contentColor = Color.White),
            // De dialoog opent terwijl OK nog ingedrukt is. Herhalingen en het loslaten van die toets negeren tot een nieuwe druk.
            modifier = modifier.onPreviewKeyEvent { event ->
                if (!heldKeyReleased && event.type == KeyEventType.KeyDown && event.nativeKeyEvent.repeatCount == 0) {
                    heldKeyReleased = true
                }
                !heldKeyReleased
            },
        ) {
            Column(modifier = Modifier.padding(32.dp)) { content() }
        }
    }
}
