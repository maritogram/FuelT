package com.maritogram.fuelt.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.maritogram.fuelt.feature.home.HomeViewModel
import com.maritogram.fuelt.feature.workingout.navigation.navigateToWorkingOutScreen
import com.maritogram.fuelt.feature.workingout.navigation.workingOutScreen
import com.maritogram.fuelt.feature.workoutgeneration.TESTROUTE
import com.maritogram.fuelt.feature.workoutgeneration.geminiLoadingScreen
import com.maritogram.fuelt.feature.workoutgeneration.navigation.WorkoutGenerationRoute
import com.maritogram.fuelt.feature.workoutgeneration.navigation.workoutGenerationScreen
import com.maritogram.fuelt.ui.FueltAppState
import com.maritogram.fuelt.ui.MainScreensRoute
import com.maritogram.fuelt.ui.MainScreensScreen


@Composable
fun FueltNavHost(
    appState: FueltAppState,
    modifier: Modifier = Modifier,
    mainVM: HomeViewModel = hiltViewModel()
    ) {


    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = MainScreensRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {

        MainScreensScreen(appState, mainVM)


        navigation<TESTROUTE>(startDestination = WorkoutGenerationRoute) {
            workoutGenerationScreen(
                parentNav = appState.navController,
            )

            geminiLoadingScreen(
                navToWorkingOutScreen = {
                    navController.navigateToWorkingOutScreen()
                },
                navController = navController
            )

            workingOutScreen(
                onExitClick = {
                    navController.popBackStack(navController.graph.startDestinationId, false)
                },
                navController,
                mainVM
            )

        }


    }

}