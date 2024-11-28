package com.maritogram.fuelt.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.maritogram.fuelt.core.database.entity.WorkoutEntity

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM WorkoutEntity")
    fun getAll(): List<WorkoutEntity>

    @Insert
    fun insertAll(vararg users: WorkoutEntity)

    @Delete
    fun delete(user: WorkoutEntity)


}