package com.maritogram.fuelt.navigation

import com.maritogram.fuelt.feature.routines.navigation.RoutinesRoute
import com.maritogram.fuelt.feature.home.navigation.HomeRoute

import kotlin.reflect.KClass


enum class AppDestinations(
    val route: KClass<*>,
) {
    TRAINER(
        route = HomeRoute::class
    ),
    ROUTINES(
        route = RoutinesRoute::class
    )


}