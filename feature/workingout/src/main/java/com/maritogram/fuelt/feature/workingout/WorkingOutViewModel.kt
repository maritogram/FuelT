package com.maritogram.fuelt.feature.workingout

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.maritogram.fuelt.core.model.exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WorkingOutViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()


    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-pro",
        apiKey = "AIzaSyCKJszH7fPMkBpZkiSezepxxqyYNXgVFwo"
    )

    private var job: Job? = null  // Add this line

    fun sendPrompt(
        prompt: String
    ) {

        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
//                        image(bitmap)
                        text(prompt)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = UiState.Success(outputContent)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }


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