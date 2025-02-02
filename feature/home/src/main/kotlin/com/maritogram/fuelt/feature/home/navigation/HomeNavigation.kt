package com.maritogram.fuelt.feature.home.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.home.HomeScreen
import com.maritogram.fuelt.feature.home.HomeViewModel
import kotlinx.serialization.Serializable


@Serializable
object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    onFabClick: () -> Unit,
    homeViewModel: HomeViewModel
) {
    composable<HomeRoute> {
        HomeScreen(
            onFabClick,
            homeViewModel,
        )


        2
    }
}










