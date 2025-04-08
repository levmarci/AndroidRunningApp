package android.runningapp.domain.model

import android.content.Context
import android.location.Location
import android.runningapp.util.RouteRecorder
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RouteBuilder @Inject constructor(
    @ApplicationContext context: Context
) {
    var route: Route = Route()
    var activityType: MutableState<ActivityType> = mutableStateOf(ActivityType.Running)
    private val routeRecorder = RouteRecorder(context)

    fun startRecording(param: (LatLng) -> Unit) {
        val recordingStartTime = System.currentTimeMillis()
        route.activityType = activityType.value
        routeRecorder.startRecording { latLng ->
            route.time = System.currentTimeMillis() - recordingStartTime
            if (route.points.isNotEmpty()) {
                val lastPoint = route.points.last()
                val results = FloatArray(1)
                Location.distanceBetween(
                    lastPoint.latitude,
                    lastPoint.longitude,
                    latLng.latitude,
                    latLng.longitude,
                    results
                )
                route.distance += results[0].toDouble()
            }
            route.points.add(latLng)
            param(latLng)
        }
    }

    fun stopRecording() {
        routeRecorder.stopRecording()
        route.reset()
    }
}