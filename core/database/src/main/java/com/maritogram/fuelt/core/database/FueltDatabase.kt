package com.maritogram.fuelt.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maritogram.fuelt.core.database.dao.WorkoutDao
import com.maritogram.fuelt.core.database.entity.WorkoutEntity

// Database class to hold the database?
@Database(entities = [
    WorkoutEntity::class
], version = 1)
abstract class FueltDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}