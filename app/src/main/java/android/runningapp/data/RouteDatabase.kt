package android.runningapp.data

import android.runningapp.data.local.converters.ActivityTypeConverter
import android.runningapp.data.local.converters.LatLngListConverter
import android.runningapp.data.local.converters.LocalDateConverter
import android.runningapp.data.local.dao.RouteDao
import android.runningapp.data.local.entities.RouteEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RouteEntity::class], version = 4)
@TypeConverters(LocalDateConverter::class, LatLngListConverter::class, ActivityTypeConverter::class)
abstract class RouteDatabase : RoomDatabase() {
    abstract val dao: RouteDao
}