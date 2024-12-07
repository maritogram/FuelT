package com.maritogram.fuelt.feature.workoutgeneration

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.maritogram.fuelt.core.model.exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Job
import kotlinx.serialization.Serializable



@HiltViewModel
class WorkingOutViewModel @Inject constructor() : ViewModel() {


    override fun onCleared() {
        super.onCleared()
        println("CORRECT cleared")
    }

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()



    fun sendPrompt(
        prompt: String
    ) {

        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-pro",
            apiKey = "AIzaSyCKJszH7fPMkBpZkiSezepxxqyYNXgVFwo"
        )

        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
//                        image(bitmap)
                        text(prompt)
                    }


                )
                println(response.text)
                response.text?.let { outputContent ->
                    _uiState.value = UiState.Success(outputContent)
                }
            } catch (e: Exception) {
                println("Error")

                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }

    }


    var exerciseBlocks by mutableStateOf<ArrayList<ArrayList<exercise>>>(
        arrayListOf(
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
    )

    // Function to update the exerciseBlocks
    fun updateExerciseBlocks(updatedBlocks: ArrayList<ArrayList<exercise>>) {
        exerciseBlocks = updatedBlocks
    }





}