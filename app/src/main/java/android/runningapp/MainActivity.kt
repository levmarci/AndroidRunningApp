package android.runningapp

import android.os.Bundle
import android.runningapp.ui.Screen
import android.runningapp.ui.theme.RunningAppTheme
import android.runningapp.viewmodel.SettingsViewModel
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()

            RunningAppTheme(darkTheme = isDarkTheme) {
                Screen()
            }
        }
    }
}