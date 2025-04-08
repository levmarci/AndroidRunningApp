package android.runningapp.domain.usecases

import android.runningapp.data.repository.IRouteRepository

class DeleteRouteUseCase(private val repository: IRouteRepository) {
    suspend operator fun invoke(id: Int) {
        repository.deleteRoute(id)
    }
}