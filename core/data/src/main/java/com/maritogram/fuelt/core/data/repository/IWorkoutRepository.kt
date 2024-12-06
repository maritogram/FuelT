package com.maritogram.fuelt.core.data.repository

import com.maritogram.fuelt.core.model.exercise
import kotlinx.coroutines.flow.Flow

interface IWorkoutRepository {

    fun getWorkouts() : List<exercise>

    // Get workouts done during the specified week
    fun getWorkouts(date: String) : Flow<List<exercise>>

    fun insertWorkout() : Unit

}