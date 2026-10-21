package dev.wahid.bare

import android.content.Intent
import android.media.tv.TvContract
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme

class MainActivity : ComponentActivity() {

    private val viewModel: AppsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val apps = viewModel.apps.collectAsStateWithLifecycle().value
            val hiddenApps = viewModel.hiddenApps.collectAsStateWithLifecycle().value
            val hdmi1InputId = viewModel.hdmi1InputId.collectAsStateWithLifecycle().value
            MaterialTheme(colorScheme = darkColorScheme()) {
                HomeScreen(
                    apps = apps,
                    hiddenApps = hiddenApps,
                    hdmi1InputId = hdmi1InputId,
                    onOpen = ::open,
                    onHide = viewModel::hide,
                    onUnhide = viewModel::unhide,
                    onAppInfo = ::showAppInfo,
                    onSwitchInput = ::switchInput,
                    onOpenSettings = ::openSettings,
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.reload()
    }

    private fun open(app: AppEntry) {
        val intent = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)
            .setClassName(app.packageName, app.activityName)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun showAppInfo(app: AppEntry) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", app.packageName, null))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun switchInput(inputId: String) {
        val intent = Intent(Intent.ACTION_VIEW, TvContract.buildChannelUriForPassthroughInput(inputId))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
