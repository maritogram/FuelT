package com.maritogram.fuelt.core.database.di

import android.content.Context
import androidx.room.Room
import com.maritogram.fuelt.core.database.FueltDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesFueltDatabase(
        @ApplicationContext context: Context,
    ): FueltDatabase = Room.databaseBuilder(
    context,
        FueltDatabase::class.java,
        "fuelt-database"
    ).build()
}