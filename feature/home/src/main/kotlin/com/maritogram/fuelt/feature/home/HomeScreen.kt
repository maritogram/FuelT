package com.maritogram.fuelt.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maritogram.fuelt.ui.NoCompletedWorkouts
import com.maritogram.fuelt.ui.WeeklyActivity

// TODO: Create root composable


@Composable
internal fun HomeScreen(
    onFabClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {


    Scaffold(
        floatingActionButton = {
            HomeFAB(onClick = onFabClick)
        }
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

            //TODO: Maybe include this in the future.
//
//            Text(
//                "Food suggestions",
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            repeat(50) {
//                Text(it.toString())
//            }

        }
    }


}

@Composable
fun HomeFAB(onClick: () -> Unit = {}) {

    LargeFloatingActionButton(onClick = onClick) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(R.drawable.fitness_center),
            contentDescription = "Dumbbell"
        )
    }


}
