package android.runningapp.ui.map

import android.runningapp.R
import android.runningapp.domain.model.Route
import android.runningapp.viewmodel.SettingsViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline

@Composable
fun RouteMap(
    route: Route,
    interactive: Boolean = true,
    locationPermissionGranted: Boolean,
    cameraPositionState: CameraPositionState,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val settingsViewModel = SettingsViewModel(context)
    val isDarkTheme = settingsViewModel.isDarkTheme.collectAsState().value
    val mapStyle =
        if (isDarkTheme)
            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_dark)
        else
            MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_light)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = locationPermissionGranted,
                mapStyleOptions = mapStyle
            ),
            uiSettings = if (interactive) {
                MapUiSettings(myLocationButtonEnabled = true)
            } else {
                MapUiSettings(
                    zoomControlsEnabled = false,
                    zoomGesturesEnabled = false,
                    scrollGesturesEnabled = false,
                    scrollGesturesEnabledDuringRotateOrZoom = false,
                    tiltGesturesEnabled = false,
                    myLocationButtonEnabled = false
                )
            }
        ) {
            if (route.points.isNotEmpty()) {
                Polyline(
                    points = route.points.toList(),
                    color = MaterialTheme.colorScheme.secondary,
                    width = 10f
                )
            }
        }
        if (onClick != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() }
            )
        }
    }
}
