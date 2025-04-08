package android.runningapp.ui.components.route

import android.runningapp.domain.model.Route
import android.runningapp.ui.map.RecordedMapScreen
import android.runningapp.ui.map.RouteMap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.rememberCameraPositionState
import java.time.format.DateTimeFormatter

@Composable
fun RouteCard(route: Route, onDelete: () -> Unit) {
    val cameraPositionState = rememberCameraPositionState()
    if (route.points.isNotEmpty())
        cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(route.points.first(), 14f))
    var showFullScreenMap by remember { mutableStateOf(false) }
    if (showFullScreenMap) {
        RecordedMapScreen(
            route = route,
            onDismiss = { showFullScreenMap = false },
            onDelete = onDelete
        )
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showFullScreenMap = true }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                RouteDetails(route)
                Spacer(modifier = Modifier.height(16.dp))
                val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
                Text(
                    text = route.date.format(dateFormatter),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .height(150.dp)
            ) {
                RouteMap(
                    route = route,
                    interactive = false,
                    locationPermissionGranted = false,
                    cameraPositionState = cameraPositionState,
                )
            }
        }
    }
}
