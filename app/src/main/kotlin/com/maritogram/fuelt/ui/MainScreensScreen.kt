package com.maritogram.fuelt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBar
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBarItem
import com.maritogram.fuelt.core.designsystem.theme.surfaceContainerDark
import com.maritogram.fuelt.feature.home.navigation.HomeRoute
import com.maritogram.fuelt.feature.home.navigation.homeScreen
import com.maritogram.fuelt.feature.routines.navigation.routinesScreen
import com.maritogram.fuelt.feature.workoutgeneration.navigation.navigateToGeminiOnboarding


@Composable
fun MainScreens(appState: FueltAppState) {

    val nestedNavController = rememberNavController()
    val currentDestination = appState.currentDestination(nestedNavController)



    Scaffold(bottomBar =
    {
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
                    onClick = {
                        appState.navigateToTopLevelDestination(
                            destination,
                            nestedNavController
                        )
                    }
                )
            }
        }
    }) { padding ->
        Column(
            Modifier
                .padding(bottom = padding.calculateBottomPadding())
                .fillMaxSize()
        ) {
            NavHost(navController = nestedNavController, startDestination = HomeRoute) {
                homeScreen(onFabClick = appState.navController::navigateToGeminiOnboarding)
                routinesScreen()
            }


        }
    }

}