package com.maritogram.fuelt.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.home.HomeViewModel
import kotlinx.serialization.Serializable


@Serializable
object MainScreensRoute

fun NavGraphBuilder.MainScreensScreen(
    appState: FueltAppState,
    viewModel: HomeViewModel
){
    composable<MainScreensRoute> {
        MainScreens(appState, viewModel)
    }

}