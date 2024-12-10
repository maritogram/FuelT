package com.maritogram.fuelt.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maritogram.fuelt.core.designsystem.theme.onPrimaryContainerDark
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceDark
import com.maritogram.fuelt.core.designsystem.theme.tertiaryContainerDark
import com.maritogram.fuelt.core.designsystem.theme.tertiaryDark
import com.maritogram.fuelt.ui.NoCompletedWorkouts
import com.maritogram.fuelt.ui.WeeklyActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.time.Duration.Companion.seconds

// TODO: Create root composable


@Composable
internal fun HomeScreen(
    onFabClick: () -> Unit,
    viewModel: HomeViewModel,
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

            Text(
                "Welcome back, \nMario",
                style = MaterialTheme.typography.headlineMedium
            )


            Spacer(modifier = Modifier.height(13.dp))

            WeeklyActivity(viewModel.getWorkoutsThisWeek(), viewModel.weeklyGoal.value)

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                "Today's completed workouts",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(17.dp))

            if (viewModel.getCompletedWorkoutsCount() < 1)
                NoCompletedWorkouts()
            else
                for (workout in viewModel.getCompletedWorkoutss()) {
                    OutlinedCard(
                        border = BorderStroke(1.dp, tertiaryDark),
                        colors = CardDefaults.cardColors(
                            containerColor = tertiaryContainerDark,
                        ), modifier = Modifier
                            .height(60.dp)
                            .width(360.dp)
                    ) {
                        Column {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Workout completed",
                                    modifier = Modifier.padding(start = 11.dp, top = 9.dp),
                                )

                            }

                            Row(modifier = Modifier.fillMaxWidth()) {
                                // Achieved column

                                val f: NumberFormat = DecimalFormat("00")

                                val formattedElapsedTime = (
                                        workout.duration.seconds.toComponents { hours, minutes, seconds, _ ->
                                            "${f.format(hours)}:${
                                                f.format(minutes)
                                            }:${f.format(seconds)}"
                                        })
                                Text(
                                    text = "Time duration: ${formattedElapsedTime}",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    color = onSurfaceDark,
                                    modifier = Modifier.padding(start = 11.dp, bottom = 1.dp)
                                )

                            }


                        }

                    }


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
}

@Composable
fun HomeFAB(onClick: () -> Unit = {}) {
//https://stackoverflow.com/questions/65643015/animating-between-composables-in-navigation-with-compose
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        enter = slideInVertically(
            initialOffsetY = { 40 }
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + fadeOut(),
    ) {
        LargeFloatingActionButton(onClick = onClick) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(R.drawable.fitness_center),
                contentDescription = "Dumbbell"
            )
        }


    }
}
