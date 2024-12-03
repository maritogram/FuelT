package com.maritogram.fuelt.feature.workoutgeneration.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
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
    parentNav: NavController
) {
    composable<WorkoutGenerationRoute>(
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Up
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Down
            )
        },
        popEnterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        WorkoutGenerationScreen(
            // TODO: Add button functionalities
            parentNav
        )
    }
}


fun NavGraphBuilder.workoutGenerationScreenNoT(
    parentNav: NavController
) {
    composable<WorkoutGenerationRoute>(
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None}
    ) {
        WorkoutGenerationScreen(
            parentNav
        )
    }
}