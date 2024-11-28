package com.maritogram.fuelt.core.database.di

import com.maritogram.fuelt.core.database.FueltDatabase
import com.maritogram.fuelt.core.database.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesWorkoutDao(
        database: FueltDatabase
    ): WorkoutDao = database.workoutDao()
}