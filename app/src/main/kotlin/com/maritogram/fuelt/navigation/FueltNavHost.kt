package com.maritogram.fuelt.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.maritogram.fuelt.feature.routines.navigation.routinesScreen
import com.maritogram.fuelt.feature.home.navigation.HomeRoute
import com.maritogram.fuelt.feature.home.navigation.homeScreen
import com.maritogram.fuelt.feature.workoutgeneration.navigation.GeminiOnboardingRoute
import com.maritogram.fuelt.feature.workoutgeneration.navigation.WorkoutGenerationRoute
import com.maritogram.fuelt.feature.workoutgeneration.navigation.navigateToGeminiOnboarding
import com.maritogram.fuelt.feature.workoutgeneration.navigation.workoutGenerationScreen
import com.maritogram.fuelt.ui.FueltAppState
import com.maritogram.fuelt.ui.MainScreensRoute
import com.maritogram.fuelt.ui.MainScreensScreen

@Composable
fun FueltNavHost(
    appState: FueltAppState,
    modifier: Modifier = Modifier

) {



    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = MainScreensRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {

        MainScreensScreen(appState)

        navigation<GeminiOnboardingRoute>(startDestination = WorkoutGenerationRoute) {
            workoutGenerationScreen(
                parentNav = appState.navController)
        }

        composable("test"){
            Column {
                Text("Good it works.")
            }
        }

    }

}