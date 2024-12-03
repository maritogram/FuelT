package com.maritogram.fuelt.feature.workingout.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.workingout.WorkingOutScreen
import kotlinx.serialization.Serializable

@Serializable
object WorkingOutRoute


// Navcontroller extension method
fun NavController.navigateToWorkingOutScreen(navOptions: NavOptions) {
    navigate(WorkingOutRoute, navOptions)
}


// Navhost extension method
fun NavGraphBuilder.workingOutScreen(onExitClick: () -> Unit) {
    composable<WorkingOutRoute>(exitTransition = {
        slideOutOfContainer(
            animationSpec = tween(300, easing = EaseOut),
            towards = AnimatedContentTransitionScope.SlideDirection.Down
        )
    }) {
        WorkingOutScreen(onExitClick = onExitClick)
    }
}

