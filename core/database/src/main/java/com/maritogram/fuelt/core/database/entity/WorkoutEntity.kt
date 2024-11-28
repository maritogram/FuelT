package com.maritogram.fuelt.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name="date") val date: String?



)
