package android.runningapp.viewmodel

import android.runningapp.domain.model.ActivityType
import android.runningapp.domain.model.Route
import android.runningapp.domain.model.RouteBuilder
import android.runningapp.domain.usecases.RouteUseCases
import android.runningapp.util.location.CurrentLocationProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val currentLocationProvider: CurrentLocationProvider,
    private val routeBuilder: RouteBuilder,
    private val routeUseCases: RouteUseCases
) : ViewModel() {
    var isRecording by mutableStateOf(false)

    val route: Route
        get() = routeBuilder.route

    var activityType: ActivityType
        get() = routeBuilder.activityType.value
        set(value) {
            routeBuilder.activityType.value = value
        }

    var currentLatLng by mutableStateOf<LatLng?>(null)
        private set

    fun fetchInitialLocation() {
        currentLocationProvider.fetchCurrentLocation { latLng ->
            latLng?.let {
                currentLatLng = it
            }
        }
    }

    fun toggleRecording() {
        isRecording = !isRecording
        if (isRecording) {
            routeBuilder.startRecording { latLng ->
                currentLatLng = latLng
            }
        } else {
            if (route.points.isNotEmpty()) {
                viewModelScope.launch {
                    routeUseCases.saveRoute(route)
                }
            }
            routeBuilder.stopRecording()
        }
    }
}