package com.maritogram.fuelt.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBar
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBarItem
import com.maritogram.fuelt.core.designsystem.theme.primaryDark
import com.maritogram.fuelt.feature.home.HomeViewModel
import com.maritogram.fuelt.feature.home.navigation.HomeRoute
import com.maritogram.fuelt.feature.home.navigation.homeScreen
import com.maritogram.fuelt.feature.routines.navigation.routinesScreen
import com.maritogram.fuelt.feature.workoutgeneration.TESTROUTE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreens(appState: FueltAppState, homeViewModel: HomeViewModel) {

    val nestedNavController = rememberNavController()
    val currentDestination = appState.currentDestination(nestedNavController)


    var showDialog by remember { mutableStateOf(false) }

    // ... your main content ...


    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = primaryDark,
            ),
            title = {},
            actions = {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Outlined.Settings, contentDescription = "")
                }
                IconButton(onClick = {}) {
                    Image(
                        painter = painterResource(id = com.maritogram.fuelt.feature.home.R.drawable.mariopfp),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
            }
        )
    },
        bottomBar =
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
                            Icon(destination.icon, contentDescription = "idfk")
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
        }) { paddingValues ->
        Column(
            Modifier
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .consumeWindowInsets(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Top,
                    )
                )
                .fillMaxSize()
        ) {

            if (showDialog) {

                var value by remember { mutableStateOf(0) }
                var textValue by remember { mutableStateOf("") }


                Dialog(onDismissRequest = { showDialog = false }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                value = textValue,
                                onValueChange = {
                                    textValue = it

                                    val isValid = it.toIntOrNull() != null && it.isNotBlank() && it.trim() == it
                                    if (isValid) {
                                        value = it.toInt()
                                    }
                                },
                                label = { Text("Enter new value") }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Button(onClick = { value }) {
                                    Text("Save")
                                }
                                Button(onClick = {}) {
                                    Text("Cancel")
                                }
                            }
                        }
                    }
                }
            }

            NavHost(
                navController = nestedNavController,
                startDestination = HomeRoute,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }) {
                homeScreen(
                    onFabClick = { appState.navController.navigate(TESTROUTE) },
                    homeViewModel
                )
                routinesScreen()
            }
        }
    }

}