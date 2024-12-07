package com.maritogram.fuelt.feature.workingout.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.maritogram.fuelt.core.model.exercise
import com.maritogram.fuelt.feature.workingout.NEWWorkingOutViewModel
import com.maritogram.fuelt.feature.workingout.WorkingOutScreen
import com.maritogram.fuelt.feature.workoutgeneration.GeminiLoadingRoute
import com.maritogram.fuelt.feature.workoutgeneration.WorkingOutViewModel
import com.maritogram.fuelt.feature.workoutgeneration.navigation.WorkoutGenerationRoute
import kotlinx.serialization.Serializable

@Serializable
object WorkingOutRoute


// Navcontroller extension method
fun NavController.navigateToWorkingOutScreen(navOptions: NavOptions? = null) {

    navigate(WorkingOutRoute, navOptions)
}


// Navhost extension method
fun NavGraphBuilder.workingOutScreen( onExitClick: () -> Unit, navController: NavController) {
    composable<WorkingOutRoute>(exitTransition = {
        slideOutOfContainer(
            animationSpec = tween(300, easing = EaseOut),
            towards = AnimatedContentTransitionScope.SlideDirection.Down
        )
    })  {

        WorkingOutScreen(onExitClick = onExitClick)
    }
}

