package com.maritogram.fuelt.feature.workingout

import androidx.lifecycle.ViewModel
import com.maritogram.fuelt.core.model.exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorkingOutViewModel @Inject constructor() : ViewModel() {

    val state = MutableStateFlow<WorkingOutScreenState>(WorkingOutScreenState())

    var exerciseBlocks: ArrayList<ArrayList<exercise>> = arrayListOf(
        arrayListOf(
            exercise(
                name = "Test 1",
                sets = 2,
                reps = arrayListOf(1, 2),
                weight = arrayListOf(1, 2)
            ),
            exercise(
                name = "Test 2",
                sets = 2,
                reps = arrayListOf(1, 2),
                weight = arrayListOf(1, 2)
            )
        ),
        arrayListOf(
            exercise(
                name = "Test 1",
                sets = 2,
                reps = arrayListOf(1, 2),
                weight = arrayListOf(1, 2)
            ),
            exercise(
                name = "Test 2",
                sets = 2,
                reps = arrayListOf(1, 2),
                weight = arrayListOf(1, 2)
            ), exercise(
                name = "Test 3",
                sets = 2,
                reps = arrayListOf(1, 2),
                weight = arrayListOf(1, 2)
            )
        ),
        arrayListOf(
            exercise(
                name = "Test 1",
                sets = 2,
                reps = arrayListOf(1, 2),
                weight = arrayListOf(1, 2)
            )
        )


    )


    fun startTimer() {
        state.tryEmit(state.value.copy(isTimerGoing = true))
        CoroutineScope(Dispatchers.IO).launch {
            while (state.value.isTimerGoing) {
                withContext(Dispatchers.Main) {
                    state.tryEmit(state.value.copy(currentTime = state.value.currentTime + 1))


                }
                delay(1000)
            }
        }
    }

    fun stopTimer() {
        state.tryEmit(state.value.copy(isTimerGoing = false))
    }

}