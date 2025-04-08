package android.runningapp.data.repository

import android.runningapp.data.local.dao.RouteDao
import android.runningapp.data.local.entities.RouteEntity
import kotlinx.coroutines.flow.Flow

class RouteRepository(private val routeDao: RouteDao) : IRouteRepository {
    override fun getAllRoutes(): Flow<List<RouteEntity>> = routeDao.getAllRoutes()

    override fun getRouteById(id: Int): Flow<RouteEntity> = routeDao.getRouteById(id)

    override suspend fun insertRoute(route: RouteEntity) = routeDao.insertRoute(route)

    override suspend fun deleteRoute(id: Int) = routeDao.deleteRoute(id)
}