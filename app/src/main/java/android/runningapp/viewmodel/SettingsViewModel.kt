package android.runningapp.viewmodel

import android.content.Context
import android.runningapp.util.settings.SettingsPreference
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    init {
        viewModelScope.launch {
            SettingsPreference.getThemeFlow(context).collect {
                _isDarkTheme.value = it
            }
        }
    }

    fun setTheme(isDark: Boolean) {
        viewModelScope.launch {
            SettingsPreference.saveTheme(context, isDark)
        }
    }
}
