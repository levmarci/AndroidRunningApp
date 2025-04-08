package android.runningapp.domain.usecases

import android.runningapp.data.repository.IRouteRepository
import android.runningapp.data.local.entities.asRouteEntity
import android.runningapp.domain.model.Route

class SaveRouteUseCase(private val repository: IRouteRepository) {
    suspend operator fun invoke(route: Route) {
        repository.insertRoute(route.asRouteEntity())
    }
}