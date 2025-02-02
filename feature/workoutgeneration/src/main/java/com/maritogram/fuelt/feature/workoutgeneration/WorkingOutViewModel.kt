package com.maritogram.fuelt.feature.workoutgeneration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.FunctionType
import com.google.ai.client.generativeai.type.Schema
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import com.maritogram.fuelt.core.model.ExerciseData
import com.maritogram.fuelt.core.model.exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WorkingOutViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


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
            apiKey = BuildConfig.GEMINI_API,
            generationConfig = generationConfig {
                responseMimeType = "application/json"



                responseSchema = Schema(
                    name = "Exercise Blocks",
                    description = "Three blocks of exercises",
                    type = FunctionType.ARRAY,
                    items = Schema(
                        name = "Exercise List",
                        description = "A list of exercises",
                        type = FunctionType.ARRAY,
                        items = Schema(
                            name = "exercise",
                            description = "An exercise",
                            type = FunctionType.OBJECT,
                            properties = mapOf(
                                "name" to Schema(
                                    name = "name",
                                    description = "Name of the exercise",
                                    type = FunctionType.STRING,
                                    nullable = false
                                ),
                                "sets" to Schema(
                                    name = "sets",
                                    description = "Number of sets",
                                    type = FunctionType.INTEGER,
                                    nullable = false
                                ),
                                "reps" to Schema(
                                    name = "reps",
                                    description = "List of repetitions for each set",
                                    type = FunctionType.ARRAY,
                                    items = Schema(
                                        name = "rep number",
                                        description = "Rep number",
                                        type = FunctionType.INTEGER,
                                        nullable = false
                                    )
                                ),
                                "weight" to Schema(
                                    name = "weight",
                                    description = "List of weights for each set",
                                    type = FunctionType.ARRAY,
                                    items = Schema(
                                        name = "weight number",
                                        description = "Weight number",
                                        type = FunctionType.INTEGER,
                                        nullable = false
                                    )
                                ),
                                "exerciseEnum" to Schema(
                                    name = "exercise enum",
                                    description = "enum for exercise",
                                    type = FunctionType.STRING,
                                    format = "enum",
                                    enum = ExerciseData.entries.map { it.name }
                                )
                            ),
                            required = listOf("name", "sets", "reps", "weight", "exerciseEnum")
                        )
                    )
                )

            }
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


    var exerciseBlocks: ArrayList<ArrayList<exercise>>
        get() = savedStateHandle.get<ArrayList<ArrayList<exercise>>>("exerciseBlocks") ?: arrayListOf()
        set(value) { savedStateHandle.set("exerciseBlocks", value) }



    // Function to update the exerciseBlocks
    fun updateExerciseBlocks(updatedBlocks: ArrayList<ArrayList<exercise>>) {
        exerciseBlocks = updatedBlocks
    }


}