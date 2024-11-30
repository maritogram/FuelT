package com.maritogram.fuelt.feature.workoutgeneration.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.workoutgeneration.WorkoutGenerationScreen
import kotlinx.serialization.Serializable

@Serializable
object WorkoutGenerationRoute

fun NavController.navigateToWorkoutGeneration(
    navOptions: NavOptions? = null
) {
    navigate(route = WorkoutGenerationRoute, navOptions)
}

fun NavGraphBuilder.workoutGenerationScreen(
) {
    composable<WorkoutGenerationRoute> {
        WorkoutGenerationScreen(
            // TODO: Add button functionalities


        )
    }
}