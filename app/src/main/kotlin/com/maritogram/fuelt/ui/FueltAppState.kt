package com.maritogram.fuelt.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.maritogram.fuelt.feature.routines.navigation.navigateToRoutines
import com.maritogram.fuelt.feature.home.navigation.navigateToHome
import com.maritogram.fuelt.navigation.AppDestinations
import com.maritogram.fuelt.navigation.AppDestinations.ROUTINES
import com.maritogram.fuelt.navigation.AppDestinations.TRAINER


@Composable
fun rememberFueltAppState(
    // Where I create my navigation controller.
    navController: NavHostController = rememberNavController()
):FueltAppState{
    return remember(
        //TODO: Fill out later
    ) {
        FueltAppState(
            navController = navController
        )
    }

}


class FueltAppState(
    val navController: NavHostController
){

    val topLevelDestinations: List<AppDestinations> = AppDestinations.entries

    fun navigateToTopLevelDestination(appDestinations: AppDestinations) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (appDestinations) {
            TRAINER -> navController.navigateToHome(topLevelNavOptions)
            ROUTINES -> navController.navigateToRoutines(topLevelNavOptions)
        }
    }


}