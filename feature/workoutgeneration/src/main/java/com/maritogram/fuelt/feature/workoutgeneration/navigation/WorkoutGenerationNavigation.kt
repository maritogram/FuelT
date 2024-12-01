package com.maritogram.fuelt.feature.workoutgeneration.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.workoutgeneration.WorkoutGenerationScreen
import kotlinx.serialization.Serializable


@Serializable
object GeminiOnboardingRoute

@Serializable
object WorkoutGenerationRoute

fun NavController.navigateToGeminiOnboarding(navOptions: NavOptions? = null) {
    navigate(route = GeminiOnboardingRoute, navOptions)
}


//fun NavController.navigateToWorkoutGeneration(
//    navOptions: NavOptions? = null
//) {
//    navigate(route = WorkoutGenerationRoute, navOptions)
//}

fun NavGraphBuilder.workoutGenerationScreen(
    onExitClick: () -> Unit,
) {
    composable<WorkoutGenerationRoute> {
        WorkoutGenerationScreen(
            // TODO: Add button functionalities
            onExitClick = onExitClick

        )
    }
}