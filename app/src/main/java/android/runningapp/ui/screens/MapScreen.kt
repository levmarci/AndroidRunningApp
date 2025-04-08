package android.runningapp.ui.screens

import android.annotation.SuppressLint
import android.runningapp.domain.model.ActivityType
import android.runningapp.ui.components.ActivityTypeDropDown
import android.runningapp.ui.components.route.RouteDetails
import android.runningapp.ui.map.RouteMap
import android.runningapp.util.location.LocationPermissionManager
import android.runningapp.viewmodel.MapViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen() {
    val viewModel: MapViewModel = hiltViewModel()
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState()
    var bottomBarHeight = 128.dp

    LaunchedEffect(Unit) {
        viewModel.fetchInitialLocation()
    }

    LaunchedEffect(Unit, viewModel.currentLatLng) {
        viewModel.currentLatLng?.let { latLng ->
            cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
        }
    }

    LaunchedEffect(viewModel.isRecording, viewModel.currentLatLng) {
        viewModel.currentLatLng?.let { latLng ->
            cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(bottomBarHeight)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { viewModel.toggleRecording() },
                        ) {
                            Text(text = if (viewModel.isRecording) "Stop Recording" else "Start Recording")
                        }
                    }
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (viewModel.isRecording) {
                            bottomBarHeight = 160.dp
                            RouteDetails(route = viewModel.route)
                        } else {
                            ActivityTypeDropDown(
                                activityTypes = ActivityType.entries,
                                selectedActivityType = viewModel.activityType,
                                onActivityTypeSelected = { viewModel.activityType = it }
                            )
                        }
                    }
                }
            }
        }
    ) {
        RouteMap(
            route = viewModel.route,
            locationPermissionGranted = LocationPermissionManager(context).rememberLocationPermission(),
            cameraPositionState = cameraPositionState
        )
    }
}
