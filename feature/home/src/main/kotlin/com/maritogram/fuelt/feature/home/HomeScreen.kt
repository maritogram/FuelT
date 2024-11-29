package com.maritogram.fuelt.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maritogram.fuelt.ui.NoCompletedWorkouts
import com.maritogram.fuelt.ui.WeeklyActivity

// TODO: Create root composable


@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = { FloatingActionButton(onClick = {}) { } }
    )
    { _ ->

        Column(
            modifier = modifier
                .padding(horizontal = 31.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            Text("Hey", textAlign = TextAlign.Start)

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Welcome back, \nMario",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(13.dp))

            WeeklyActivity()

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                "Today's completed workouts",
                style = MaterialTheme.typography.titleMedium
            )

            NoCompletedWorkouts()

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                "Food suggestions",
                style = MaterialTheme.typography.titleMedium
            )

            repeat(50) {
                Text(it.toString())
            }

        }
    }


}

@Composable
fun Icon(add: Any, s: String) {

}
