package android.runningapp.data.repository

import android.runningapp.data.local.entities.RouteEntity
import kotlinx.coroutines.flow.Flow

interface IRouteRepository {
    fun getAllRoutes(): Flow<List<RouteEntity>>

    fun getRouteById(id: Int): Flow<RouteEntity>

    suspend fun insertRoute(route: RouteEntity)

    suspend fun deleteRoute(id: Int)
}