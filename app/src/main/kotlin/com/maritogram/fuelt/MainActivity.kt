package com.maritogram.fuelt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import com.maritogram.fuelt.ui.FueltApp
import com.maritogram.fuelt.ui.rememberFueltAppState


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val appState = rememberFueltAppState()
            FueltApp(appState)
        }
    }
}


