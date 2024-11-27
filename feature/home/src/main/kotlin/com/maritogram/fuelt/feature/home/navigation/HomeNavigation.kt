package com.maritogram.fuelt.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.home.HomeScreen
import kotlinx.serialization.Serializable


@Serializable object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.trainerScreen(

){
    composable<HomeRoute> {
        HomeScreen()
    }
}










