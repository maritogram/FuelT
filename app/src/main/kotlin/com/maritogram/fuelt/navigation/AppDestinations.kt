package com.maritogram.fuelt.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.maritogram.fuelt.feature.routines.navigation.RoutinesRoute
import com.maritogram.fuelt.feature.home.navigation.HomeRoute

import kotlin.reflect.KClass


enum class AppDestinations(
    val route: KClass<*>,
    val fab: Boolean,
    val tabTitle: String,
    val icon: ImageVector
) {
    HOME(
        route = HomeRoute::class,
        fab = true,
        tabTitle = "Home",
        icon = Icons.Default.Home

    ),
    ROUTINES(
        route = RoutinesRoute::class,
        fab = false,
        tabTitle = "Routines",
        icon = Icons.AutoMirrored.Filled.List
    )


}