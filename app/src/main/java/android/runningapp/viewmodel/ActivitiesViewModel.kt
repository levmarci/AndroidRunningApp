package android.runningapp.viewmodel

import android.runningapp.domain.model.Route
import android.runningapp.domain.usecases.RouteUseCases
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val routeUseCases: RouteUseCases
) : ViewModel() {

    var routes by mutableStateOf<List<Route>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            routes = routeUseCases.loadRoutes().getOrDefault(emptyList())
        }
    }

    fun delete(route: Route) {
        viewModelScope.launch {
            routeUseCases.deleteRoute(route.id)
            routes = routeUseCases.loadRoutes().getOrDefault(emptyList())
        }
    }
}