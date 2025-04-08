package android.runningapp.data.local.dao

import android.runningapp.data.local.entities.RouteEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RouteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: RouteEntity)

    @Query("SELECT * FROM route_table")
    fun getAllRoutes(): Flow<List<RouteEntity>>

    @Query("SELECT * FROM route_table WHERE id = :id")
    fun getRouteById(id: Int): Flow<RouteEntity>

    @Query("DELETE FROM route_table WHERE id = :id")
    suspend fun deleteRoute(id: Int)

}