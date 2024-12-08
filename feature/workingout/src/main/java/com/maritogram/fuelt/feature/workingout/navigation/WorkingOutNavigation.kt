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
import com.maritogram.fuelt.feature.home.HomeViewModel
import com.maritogram.fuelt.feature.workingout.NEWWorkingOutViewModel
import com.maritogram.fuelt.feature.workingout.WorkingOutScreen
import com.maritogram.fuelt.feature.workoutgeneration.TESTROUTE
import com.maritogram.fuelt.feature.workoutgeneration.WorkingOutViewModel
import kotlinx.serialization.Serializable

@Serializable
object WorkingOutRoute


// Navcontroller extension method
fun NavController.navigateToWorkingOutScreen(navOptions: NavOptions? = null) {

    navigate(WorkingOutRoute, navOptions)
}


// Navhost extension method
fun NavGraphBuilder.workingOutScreen(
    onExitClick: () -> Unit,
    navController: NavController,
    vm: HomeViewModel
) {
    composable<WorkingOutRoute>(exitTransition = {
        slideOutOfContainer(
            animationSpec = tween(300, easing = EaseOut),
            towards = AnimatedContentTransitionScope.SlideDirection.Down
        )
    }) { backstackEntry ->

        val parentEntry = remember(backstackEntry) {
            navController.getBackStackEntry(TESTROUTE)
        }
        val parentViewModel = hiltViewModel<WorkingOutViewModel>(parentEntry)


        val thisVM = hiltViewModel<NEWWorkingOutViewModel>()

        WorkingOutScreen(
            onExitClick = onExitClick,
            exerciseBlocksvm = parentViewModel,
            viewModel = thisVM,
            navController = navController,
            homeViewModel = vm
        )
    }
}

