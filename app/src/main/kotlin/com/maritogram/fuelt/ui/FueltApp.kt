package com.maritogram.fuelt.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBar
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBarItem
import com.maritogram.fuelt.navigation.FueltNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FueltApp(
    appState: FueltAppState
) {
    Scaffold(
        //contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            FueltNavigationBar {
                appState.topLevelDestinations.forEach { destination ->
                    FueltNavigationBarItem(selected = true, icon = {
                        Icon(Icons.Filled.Home, contentDescription = "idfk")
                    }, onClick = { appState.navigateToTopLevelDestination(destination) }
                    )
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