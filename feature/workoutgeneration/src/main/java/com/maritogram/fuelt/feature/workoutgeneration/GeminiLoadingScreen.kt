package com.maritogram.fuelt.feature.workoutgeneration

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.workoutgeneration.navigation.WorkoutGenerationRoute
import kotlinx.serialization.Serializable


@Serializable
object GeminiLoadingRoute

fun NavGraphBuilder.geminiLoadingScreen(
) {
    composable<GeminiLoadingRoute>(
        // TODO: Change to some other animation.
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Up
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Down
            )
        },
    ) {
        GeminiLoadingScreen()
    }
}

@Composable
fun GeminiLoadingScreen() {
    Scaffold { p ->

        Column(modifier = Modifier.fillMaxSize()) {
            Text("Your workout is being generated")
        }


    }


}