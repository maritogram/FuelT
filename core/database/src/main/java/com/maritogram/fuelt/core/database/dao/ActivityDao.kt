package com.maritogram.fuelt.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.maritogram.fuelt.core.database.entity.Activity

@Dao
interface ActivityDao {
    @Query("SELECT * FROM Activity")
    fun getAll(): List<Activity>

    @Insert
    fun insertAll(vararg users: Activity)

    @Delete
    fun delete(user: Activity)


}