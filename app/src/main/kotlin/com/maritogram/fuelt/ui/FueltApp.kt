package com.maritogram.fuelt.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBar
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBarItem
import com.maritogram.fuelt.navigation.AppDestinations
import com.maritogram.fuelt.navigation.FueltNavHost
import kotlin.reflect.KClass

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FueltApp(
    appState: FueltAppState
) {
    val currentDestination = appState.currentDestination

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                appState.inTopLevelDestination,
                enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })

            ) {
                FueltNavigationBar {
                    appState.topLevelDestinations.forEach { destination ->
                        val selected = currentDestination
                            .isRouteInHierarchy(destination.route)
                        FueltNavigationBarItem(
                            selected = selected,
                            label = {
                                if (selected)
                                    Text(destination.tabTitle)
                            },
                            icon = {
                                Icon(Icons.Filled.Home, contentDescription = "idfk")
                            },
                            onClick = { appState.navigateToTopLevelDestination(destination) }
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .consumeWindowInsets(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    )
                )


        ) {
            FueltNavHost(appState)
        }
    }
}


// Extension function to basically check if said route is part of the hierarchy of the destination
private fun NavDestination?.isRouteInHierarchy(route: KClass<*>): Boolean =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false