package com.maritogram.fuelt.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBar
import com.maritogram.fuelt.core.designsystem.component.FueltNavigationBarItem
import com.maritogram.fuelt.navigation.FueltNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FueltApp(
    appState: FueltAppState
) {
    Scaffold(
        bottomBar = {
            FueltNavigationBar {
                appState.topLevelDestinations.forEach { destination ->
                    FueltNavigationBarItem(
                        selected = true,
                        icon = {
                            Icon(Icons.Filled.Home, contentDescription = "idfk")
                        },
                        onClick = { appState.navigateToTopLevelDestination(destination) }

                    )

                }
            }


        }
    )
    {
        padding->
        Column (
            Modifier.padding(padding)
        )
        {
            FueltNavHost(appState)
        }
    }
}