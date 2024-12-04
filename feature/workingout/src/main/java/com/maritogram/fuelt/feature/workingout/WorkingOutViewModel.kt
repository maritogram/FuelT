package com.maritogram.fuelt.feature.workingout

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorkingOutViewModel @Inject constructor() : ViewModel() {

    val state = MutableStateFlow<WorkingOutScreenState>(WorkingOutScreenState())


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