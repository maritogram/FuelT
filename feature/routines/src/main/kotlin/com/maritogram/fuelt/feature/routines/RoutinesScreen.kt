package com.maritogram.fuelt.feature.routines

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceDark


@Composable
fun RoutinesScreen() {
    Scaffold(
        floatingActionButton = { RoutinesFAB({}) }
    ) { p ->
        Column(
            modifier = Modifier
                .padding(horizontal = 31.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                "Your workouts",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(210.dp))


            Column(Modifier.padding(top = 24.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        "(ʘᗩʘ')",
                        fontWeight = FontWeight.Medium,
                        fontSize = 28.sp,
                        color = onSurfaceDark.copy(alpha = 0.6f)
                    )

                }

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        "You don't have any routines yet.",
                        fontWeight = FontWeight.Normal,
                        fontSize = 11.sp,
                        color = onSurfaceDark.copy(alpha = 0.4f)
                    )

                }
            }

        }


    }

}


@Composable
fun RoutinesFAB(onClick: () -> Unit = {}) {
    //https://stackoverflow.com/questions/65643015/animating-between-composables-in-navigation-with-compose
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        enter = slideInVertically(
            initialOffsetY = { 40 }
        )  + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically()  + fadeOut(),
    ) {

        LargeFloatingActionButton(onClick = onClick) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "Create routine"
            )
        }
    }


}
