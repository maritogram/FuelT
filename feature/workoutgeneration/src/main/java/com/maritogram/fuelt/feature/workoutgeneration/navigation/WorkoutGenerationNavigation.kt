package com.maritogram.fuelt.feature.workoutgeneration.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
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
    composable<WorkoutGenerationRoute>(enterTransition = {
        slideIntoContainer(
            animationSpec = tween(300, easing = EaseIn),
            towards = AnimatedContentTransitionScope.SlideDirection.Up
        )
    }, exitTransition = { slideOutOfContainer(animationSpec = tween(300, easing = EaseOut), towards = AnimatedContentTransitionScope.SlideDirection.Down)}
    ) {
        WorkoutGenerationScreen(
            // TODO: Add button functionalities
            onExitClick = onExitClick

        )
    }
}