package com.maritogram.fuelt.feature.workoutgeneration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutGenerationScreen(
){

    val navigationBarSize = WindowInsets.navigationBars.getBottom(LocalDensity.current);


    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.offset(y= (-50).dp)
    ) {  }


}