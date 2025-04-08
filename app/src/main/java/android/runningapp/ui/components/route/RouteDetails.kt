package android.runningapp.ui.components.route

import android.runningapp.domain.model.Route
import android.runningapp.ui.components.DataRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun RouteDetails(route: Route) {
    Column {
        Text(
            text = route.activityType.name,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        DataRow(label = "Distance:", value = "${route.distance.roundToInt()} m")

        val seconds = route.time / 1000
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, secs)
        DataRow(label = "Time:", value = timeFormatted)

        val avgSpeed = if (route.time > 0L) route.distance * 3600.0 / route.time else 0.0
        DataRow(label = "Speed:", value = String.format("%.2f km/h", avgSpeed))
    }
}