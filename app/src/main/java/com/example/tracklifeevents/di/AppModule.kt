package com.example.tracklifeevents.di

import android.content.Context
import androidx.room.Room
import com.example.tracklifeevents.data.repo.EventRepoImpl
import com.example.tracklifeevents.local_source.AppDatabase
import com.example.tracklifeevents.local_source.dao.EventDao
import com.example.tracklifeevents.repo_interface.EventRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "app_database"
        ).build()
    }

    @Provides
    fun provideEventDao(appDatabase: AppDatabase): EventDao {
        return appDatabase.eventDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {
    @Singleton
    @Binds
    abstract fun bindEventRepo(eventRepoImpl: EventRepoImpl): EventRepo
}