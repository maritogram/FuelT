package com.maritogram.fuelt.core.data.repository

import com.maritogram.fuelt.core.model.exercise
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// Repository with no database. For demo purposes
    class NoDBWorkoutRepository @Inject constructor(): IWorkoutRepository {
//    private val workouts = arrayListOf(Workout("test1", "date1"), Workout("test2", "date2"))

    override fun getWorkouts(): List<exercise> {
        return emptyList()
    }

    override fun insertWorkout() {

    }

    override fun getWorkouts(date: String): Flow<List<exercise>> {
        TODO("Not yet implemented")
    }

}