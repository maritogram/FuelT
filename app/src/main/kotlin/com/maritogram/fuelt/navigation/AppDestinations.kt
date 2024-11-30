package com.maritogram.fuelt.navigation

import com.maritogram.fuelt.feature.routines.navigation.RoutinesRoute
import com.maritogram.fuelt.feature.home.navigation.HomeRoute

import kotlin.reflect.KClass


enum class AppDestinations(
    val route: KClass<*>,
    val fab: Boolean,
    val tabTitle: String
) {
    HOME(
        route = HomeRoute::class,
        fab = true,
        tabTitle = "Home",

    ),
    ROUTINES(
        route = RoutinesRoute::class,
        fab = false,
        tabTitle = "Routines"
    )


}