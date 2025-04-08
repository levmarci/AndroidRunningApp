package android.runningapp.di

import android.content.Context
import android.runningapp.data.RouteDatabase
import android.runningapp.data.local.dao.RouteDao
import android.runningapp.data.repository.IRouteRepository
import android.runningapp.data.repository.RouteRepository
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRouteDatabase(@ApplicationContext context: Context): RouteDatabase {
        return Room.databaseBuilder(
            context,
            RouteDatabase::class.java,
            "route_database"
        )
            //.fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRouteDao(database: RouteDatabase): RouteDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun provideIRouteRepository(routeDao: RouteDao): IRouteRepository {
        return RouteRepository(routeDao)
    }

    @Provides
    @Singleton
    fun provideRouteRepository(routeDao: RouteDao): RouteRepository {
        return RouteRepository(routeDao)
    }
}