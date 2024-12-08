package com.maritogram.fuelt.feature.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.maritogram.fuelt.core.data.repository.IWorkoutRepository
import com.maritogram.fuelt.core.model.exercise
import com.maritogram.fuelt.core.model.workout
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
): ViewModel() {


    val weeklyGoal = mutableIntStateOf(3)

    var completedWorkouts: ArrayList<workout> = ArrayList<workout>()

    fun getCompletedWorkoutsCount(): Int {
        return completedWorkouts.size
    }

    fun getCompletedWorkoutss(): ArrayList<workout> {
        return completedWorkouts
    }

    fun addCompletedWorkout(workout: workout) {
        completedWorkouts.add(workout)
    }

    fun getWorkoutsThisWeek(): List<workout> {
        val now = LocalDate.now()
        val weekFields = WeekFields.of(Locale.getDefault()) // Get week definition for user's locale
        val startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)) // Adjust to your week start day
        val endOfWeek = startOfWeek.plusDays(6)

        return completedWorkouts.filter { workout ->
            val completionDate = workout.completionDate
            completionDate.isAfter(startOfWeek.minusDays(1)) && completionDate.isBefore(endOfWeek.plusDays(1))
        }
    }

}