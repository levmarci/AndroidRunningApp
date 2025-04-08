package android.runningapp.ui.screens

import android.runningapp.ui.components.route.RouteCard
import android.runningapp.viewmodel.ActivitiesViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ActivitiesScreen() {
    val viewModel: ActivitiesViewModel = hiltViewModel()
    val routes = viewModel.routes

    LazyColumn {
        items(routes.reversed()) { route ->
            RouteCard(route = route, onDelete = { viewModel.delete(route) })
        }
    }
}