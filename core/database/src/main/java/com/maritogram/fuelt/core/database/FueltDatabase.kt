package com.maritogram.fuelt.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maritogram.fuelt.core.database.dao.ActivityDao
import com.maritogram.fuelt.core.database.entity.Activity

// Database class to hold the database?
@Database(entities = [
    Activity::class
], version = 1)
abstract class FueltDatabase : RoomDatabase() {
    abstract fun ActivityDao(): ActivityDao
}