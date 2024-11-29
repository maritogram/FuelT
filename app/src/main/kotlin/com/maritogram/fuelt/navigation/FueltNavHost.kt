package com.maritogram.fuelt.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.maritogram.fuelt.feature.routines.navigation.routinesScreen
import com.maritogram.fuelt.feature.home.navigation.HomeRoute
import com.maritogram.fuelt.feature.home.navigation.trainerScreen
import com.maritogram.fuelt.ui.FueltAppState

@Composable
fun FueltNavHost(
    appState: FueltAppState,
    modifier: Modifier = Modifier

) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition =  { ExitTransition.None }
    ) {
        trainerScreen()
        routinesScreen()
    }

}