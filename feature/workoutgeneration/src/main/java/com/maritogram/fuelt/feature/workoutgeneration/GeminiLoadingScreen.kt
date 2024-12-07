package com.maritogram.fuelt.feature.workoutgeneration

import androidx.activity.ComponentActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.maritogram.fuelt.core.designsystem.theme.outlineDark
import com.maritogram.fuelt.core.model.exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
object TESTROUTE

@Serializable
data class GeminiLoadingRoute(val timeFrame: String, val intensity: String, val bodySection: String)


fun NavGraphBuilder.geminiLoadingScreen(
    navController: NavController,
    navToWorkingOutScreen: () -> Unit,
) {
    composable<GeminiLoadingRoute>(
        // TODO: Change to some other animation. This one is fine though I believe.
        enterTransition = { EnterTransition.None },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        val RouteObject: GeminiLoadingRoute = it.toRoute()
        
        val parentEntry = remember(it) {
            navController.getBackStackEntry(TESTROUTE)
        }
        val parentViewModel = hiltViewModel<WorkingOutViewModel>(parentEntry)

        GeminiLoadingScreen(
            RouteObject.timeFrame,
            RouteObject.intensity,
            RouteObject.bodySection,
            navToWorkingOutScreen = navToWorkingOutScreen,
            viewModel = parentViewModel
        )
    }
}

@Composable
fun GeminiLoadingScreen(
    timeFrame: String,
    intensity: String,
    bodySection: String,
    viewModel: WorkingOutViewModel,
    navToWorkingOutScreen: () -> Unit
) {


    LaunchedEffect(key1 = Unit) {
        viewModel.sendPrompt(
            "You are Gemini, a large language model from Google AI.\n" +
                    "\n" +
                    "I'm building a workout app and need your help generating workout routines. I'll provide you with:\n" +
                    "\n" +
                    "* **{${timeFrame}}:** A string representing the desired duration of the workout (e.g., \"15 minutes\", \"30 minutes\", \"1 hour\").\n" +
                    "* **{${intensity}}:** A string indicating the intensity of the workout (e.g., \"Beginner\", \"Intermediate\", \"Advanced\").\n" +
                    "* **{${bodySection}}:** A string specifying the target body section (e.g., \"Upper Body\", \"Lower Body\", \"Core\", \"Full Body\").\n" +
                    "\n" +
                    "Based on this information, generate an ArrayList (as JSON) of size 3 containing ArrayLists of exercises. Each exercise should follow this structure (in JSON format):\n" +
                    "\n" +
                    "Example of the desired JSON output:\n" +
                    "\n" +
                    "[\n" +
                    "  [\n" +
                    "    {\n" +
                    "      \"name\": \"Bench Press\",\n" +
                    "      \"sets\": 3,\n" +
                    "      \"reps\": [8, 10, 12],\n" +
                    "      \"weight\": [100, 110, 120]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"name\": \"Dumbbell Rows\",\n" +
                    "      \"sets\": 3,\n" +
                    "      \"reps\": [10, 12, 15],\n" +
                    "      \"weight\": [40, 45, 50]\n" +
                    "    }\n" +
                    "    // ... more exercises for the first ArrayList\n" +
                    "  ],\n" +
                    "  [\n" +
                    "    // ... exercises for the second ArrayList\n" +
                    "  ],\n" +
                    "  [\n" +
                    "    // ... exercises for the third ArrayList\n" +
                    "  ]\n" +
                    "]\n" +
                    "\n" +
                    "\n" +
                    "Guidelines:\n" +
                    "\n" +
                    "* The generated exercises should be appropriate for the given `timeFrame`, `intensityLevel`, and `bodySection`.\n" +
                    "* Consider rest times between sets and exercises to fit the `timeFrame`.\n" +
                    "* Adjust the number of sets, reps, and weight according to the `intensityLevel`.\n" +
                    "* Include a variety of exercises for the specified `bodySection`.\n" +
                    "* Ensure the `weight` values are integers and the `reps` and `weight` arrays have the exact same number of elements as indicated by the `sets` value. For example, if `sets` is 3, `reps` and `weight` should each be an array of 3 integers.\n" +
                    "* Ensure the JSON output is valid and well-formatted.\n" +
                    "* Do not include image or video links in the JSON output. \n" +
                    "* Give me the JSON in a string form that I can deserialize with Kotlin. \n" +
                    "* Give me the JSON in a string form that I can deserialize with Kotlin. Do not include any backticks (```) in the output. \n" +
                    "* Give me the JSON output as if it were a string variable in a Kotlin file (JUST PURE TEXT), ready to be deserialized." +
                    "* \"PLEASE DONT WRAP IT IN QUOTES, AND DO NOT INCLUDE the back slash n's JUST THE JSON PLEASE."
        )
    }

    Scaffold { p ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = p.calculateTopPadding(), bottom = p.calculateBottomPadding())
        ) {

            // Very funny move incoming
            Spacer(modifier = Modifier.height(278.dp))

            Text(
                "Your workout is being generated",
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp),


                )

            Text(
                "The model will try and create a personalized workout with your preferences.  If you don't like an exercise, you can always regenerate it for a new one!",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp),
                color = outlineDark

            )


            // My beloved spacer
            Spacer(modifier = Modifier.height(14.dp))


            // Loading section
            GeminiLoadBar(viewModel = viewModel, navToWorkingOutScreen)


        }


    }


}


@Composable
fun GeminiLoadBar(
    viewModel: WorkingOutViewModel,
    navToWorkingOutScreen: () -> Unit
) {


    val uiState by viewModel.uiState.collectAsState()

    val json = Json { ignoreUnknownKeys = true } // Create a Json instance

    if (uiState is UiState.Loading)
        LinearProgressIndicator(
            modifier = Modifier.width(366.dp)
        )
    else {
        if (uiState is UiState.Error) {
            val result = (uiState as UiState.Error).errorMessage
            Text(result)

        } else if (uiState is UiState.Success) {
            val result = (uiState as UiState.Success).outputText
            val exerciseBlocks: List<List<exercise>> = json.decodeFromString(result)

            viewModel.updateExerciseBlocks(exerciseBlocks as ArrayList<ArrayList<exercise>>)
            navToWorkingOutScreen()

        }
    }

//    viewModel.clearJob()


}