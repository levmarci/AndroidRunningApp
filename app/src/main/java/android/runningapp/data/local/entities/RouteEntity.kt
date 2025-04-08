package android.runningapp.data.local.entities

import android.runningapp.domain.model.ActivityType
import android.runningapp.domain.model.Route
import androidx.compose.runtime.mutableStateListOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import java.time.LocalDateTime

@Entity(tableName = "route_table")
data class RouteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val points: List<LatLng>,
    var time: Long,
    var distance: Double,
    var date: LocalDateTime,
    var activityType: ActivityType
)

fun RouteEntity.asRoute(): Route = Route(
    id = id,
    points = mutableStateListOf<LatLng>().apply { addAll(points) },
    time = time,
    distance = distance,
    date = date,
    activityType = activityType
)

fun Route.asRouteEntity(): RouteEntity = RouteEntity(
    id = id,
    points = points.toList(),
    time = time,
    distance = distance,
    date = date,
    activityType = activityType
)
