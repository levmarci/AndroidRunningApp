package android.runningapp.domain.usecases

import android.runningapp.data.repository.IRouteRepository
import android.runningapp.data.local.entities.asRoute
import android.runningapp.domain.model.Route
import kotlinx.coroutines.flow.first
import java.io.IOException

class LoadRoutesUseCase(private val repository: IRouteRepository) {
    suspend operator fun invoke(): Result<List<Route>> {
        return try {
            Result.success(repository.getAllRoutes().first().map { it.asRoute() })
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}