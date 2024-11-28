package com.maritogram.fuelt.core.data.repository

import com.maritogram.fuelt.core.model.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


// Repository with no database. For demo purposes
class NoDBWorkoutRepository @Inject constructor(): IWorkoutRepository {
    private val workouts = arrayListOf(Workout("test1", "date1"), Workout("test2", "date2"))


    override fun getWorkouts(): List<Workout> = workouts

    override fun insertWorkout() {
        workouts.add(Workout("test3", "date3"))
    }


    override fun getWorkouts(date: String): Flow<List<Workout>> {
        TODO("Not yet implemented")
    }

}