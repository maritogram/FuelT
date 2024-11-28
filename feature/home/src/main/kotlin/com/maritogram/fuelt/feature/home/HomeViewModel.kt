package com.maritogram.fuelt.feature.home

import androidx.lifecycle.ViewModel
import com.maritogram.fuelt.core.data.repository.IWorkoutRepository
import com.maritogram.fuelt.core.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val WorkoutRepository: IWorkoutRepository

): ViewModel() {

    fun getWorkouts() : List<Workout> = WorkoutRepository.getWorkouts()

    fun insertWorkout() : Unit = WorkoutRepository.insertWorkout()


}