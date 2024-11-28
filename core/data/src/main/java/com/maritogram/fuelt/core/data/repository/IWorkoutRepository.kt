package com.maritogram.fuelt.core.data.repository

import com.maritogram.fuelt.core.model.Workout
import kotlinx.coroutines.flow.Flow

interface IWorkoutRepository {

    fun getWorkouts() : List<Workout>

    // Get workouts done during the specified week
    fun getWorkouts(date: String) : Flow<List<Workout>>

}