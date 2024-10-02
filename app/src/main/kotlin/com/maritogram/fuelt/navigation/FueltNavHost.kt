package com.maritogram.fuelt.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.maritogram.fuelt.feature.trainer.navigation.TrainerRoute
import com.maritogram.fuelt.feature.trainer.navigation.trainerScreen
import com.maritogram.fuelt.ui.FueltAppState

@Composable
fun FueltNavHost(
    appState: FueltAppState,
    modifier: Modifier = Modifier

){
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = TrainerRoute,
        modifier = modifier
    ){
        trainerScreen()
    }

}