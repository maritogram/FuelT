package com.maritogram.fuelt.core.database.entity

import android.icu.util.Calendar.WeekData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Activity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name="date") val date: String?



    )
