package com.maritogram.fuelt.core.data.di

import com.maritogram.fuelt.core.data.repository.IWorkoutRepository
import com.maritogram.fuelt.core.data.repository.NoDBWorkoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsWorkoutRepository(
        workoutRepository: NoDBWorkoutRepository
    ) : IWorkoutRepository

}