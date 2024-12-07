package com.maritogram.fuelt.feature.workoutgeneration

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.maritogram.fuelt.core.designsystem.theme.outlineDark
import com.maritogram.fuelt.core.model.exercise
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
            "I'm building a workout app and need your help generating workout routines. I'll provide you with:\n" +
                    "\n" +
                    "* **{${timeFrame}}:** A string representing the desired duration of the workout (e.g., \"15 minutes\", \"30 minutes\", \"1 hour\").\n" +
                    "* **{${intensity}}:** A string indicating the intensity of the workout (e.g., \"Beginner\", \"Intermediate\", \"Advanced\").\n" +
                    "* **{${bodySection}}:** A string specifying the target body section (e.g., \"Upper Body\", \"Lower Body\", \"Core\", \"Full Body\").\n" +
                    "\n" +
                    "The output should be an ArrayList of exactly 3 elements. \n" +
                    "Each element in the ArrayList should be an ArrayList of exercises.\n" +
                    "Each exercise must have the following properties:\n" +
                    "  - name (string)\n" +
                    "  - sets (integer)\n" +
                    "  - reps (an array of integers with the same number of elements as 'sets')\n" +
                    "  - weight (an array of integers with the same number of elements as 'sets')"
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