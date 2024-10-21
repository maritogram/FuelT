package com.maritogram.fuelt.feature.routines.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.routines.RoutinesScreen
import kotlinx.serialization.Serializable

/* Apparently we need the serializable annotation to be able to save the current route/destination
 when the state is saved or restored. Not sure exactly how. It serializes to string, maybe, which
 means we can use a string as a route too. */
@Serializable object RoutinesRoute


fun NavController.navigateToRoutines(navOptions: NavOptions)
    = navigate(route = RoutinesRoute, navOptions = navOptions )

fun NavGraphBuilder.routinesScreen(){
    composable<RoutinesRoute>{
        RoutinesScreen()
    }

}
