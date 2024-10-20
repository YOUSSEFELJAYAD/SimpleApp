package com.simple

import android.app.UiModeManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.getSystemService
import androidx.navigation.compose.rememberNavController
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

import com.simple.theme.AnimatedAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent {
            MainView(this)
        }
        val appUpdateManager = AppUpdateManagerFactory.create(this@MainActivity)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    1000
                )
            }
        }

    }
    internal fun setUiMode(isLight: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val uiModeManager = getSystemService<UiModeManager>()!!
            uiModeManager.setApplicationNightMode(
                if (isLight) UiModeManager.MODE_NIGHT_NO else UiModeManager.MODE_NIGHT_YES,
            )
        } else {
            AppCompatDelegate.setDefaultNightMode(
                if (isLight) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES,
            )
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { !isLight },
            navigationBarStyle = SystemBarStyle.auto(
                DefaultLightScrim,
                DefaultDarkScrim
            ) { !isLight },
        )
    }
    private val DefaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
    private val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
}


@Composable
fun MainView(activity: MainActivity) {
    var isLightThemeUser by rememberSaveable { mutableStateOf<Boolean?>(null) }
    val isLightThemeFinal = isLightThemeUser ?: !isSystemInDarkTheme()
    val navController = rememberNavController()
    var isSplashScreenFinished by remember { mutableStateOf(false) }
    AnimatedAppTheme(
        isLightTheme = isLightThemeFinal,
        onThemeToggle = { isLight ->
            isLightThemeUser = isLight
            activity.setUiMode(isLight)
        },
    ) { onThemeToggle ->
        if (isSplashScreenFinished.not()) {
            LaunchedEffect(Unit) {
                delay(1500)
                isSplashScreenFinished = true
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text("Splash Screen", style = OrbitTheme.typography.title1)
            }
        } else
            NavGraph(onThemeToggle = onThemeToggle, navController)
    }
}





