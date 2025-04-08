package android.runningapp.domain.usecases

import android.runningapp.data.repository.IRouteRepository
import javax.inject.Inject

class RouteUseCases @Inject constructor(
    repository: IRouteRepository
) {
    val saveRoute = SaveRouteUseCase(repository)
    val loadRoute = LoadRouteUseCase(repository)
    val loadRoutes = LoadRoutesUseCase(repository)
    val deleteRoute = DeleteRouteUseCase(repository)
}