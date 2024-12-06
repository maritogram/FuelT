package com.maritogram.fuelt.feature.home

import androidx.lifecycle.ViewModel
import com.maritogram.fuelt.core.data.repository.IWorkoutRepository
import com.maritogram.fuelt.core.model.exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val WorkoutRepository: IWorkoutRepository

): ViewModel() {

    fun getWorkouts() : List<exercise> = WorkoutRepository.getWorkouts()

    fun insertWorkout() : Unit = WorkoutRepository.insertWorkout()


}