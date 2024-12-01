package com.maritogram.fuelt.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object MainScreensRoute

fun NavController.NavigateToMainScreens(navOptions: NavOptions? = null) {
    navigate(route = MainScreensRoute, navOptions)

}

fun NavGraphBuilder.MainScreensScreen(
    appState: FueltAppState
){
    composable<MainScreensRoute> {
        MainScreens(appState)
    }

}