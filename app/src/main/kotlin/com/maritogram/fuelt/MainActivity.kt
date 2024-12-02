package com.maritogram.fuelt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import com.maritogram.fuelt.core.designsystem.theme.FueltTheme
import com.maritogram.fuelt.ui.FueltApp
import com.maritogram.fuelt.ui.rememberFueltAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )

        setContent {
            // Creating our app state
            val appState = rememberFueltAppState()
            FueltTheme(
                darkTheme = true
            ) {
                FueltApp(appState)
            }
        }
    }
}


