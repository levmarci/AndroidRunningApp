package android.runningapp.domain.model

import androidx.compose.runtime.mutableStateListOf
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

data class Route(
    val id: Int = 0,
    var points: MutableList<LatLng> = mutableStateListOf(),
    var time: Long = 0L,
    var distance: Double = 0.0,
    var date: LocalDateTime = LocalDateTime.now(),
    var activityType: ActivityType = ActivityType.Running
) {
    fun reset() {
        points.clear()
        time = 0
        distance = 0.0
        date = LocalDateTime.now()
        activityType = ActivityType.Running
    }
}