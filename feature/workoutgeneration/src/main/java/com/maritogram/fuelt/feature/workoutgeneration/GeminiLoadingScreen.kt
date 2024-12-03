package com.maritogram.fuelt.feature.workoutgeneration

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maritogram.fuelt.core.designsystem.theme.outlineDark
import com.maritogram.fuelt.feature.workoutgeneration.navigation.WorkoutGenerationRoute
import kotlinx.serialization.Serializable


@Serializable
object GeminiLoadingRoute

fun NavGraphBuilder.geminiLoadingScreen(
) {
    composable<GeminiLoadingRoute>(
        // TODO: Change to some other animation. This one is fine though I believe.
        enterTransition = { EnterTransition.None },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        GeminiLoadingScreen()
    }
}

@Composable
fun GeminiLoadingScreen() {
    Scaffold { p ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = p.calculateTopPadding(), bottom = p.calculateBottomPadding())
        ) {

            // Very funny move incoming
            Spacer(modifier = Modifier.height(278.dp))

            Text(
                "Your workout is being generated",
                fontSize = 28.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp),



            )

            Text(
                "The model will try and create a personalized workout with your preferences.  If you don't like an exercise, you can always regenerate it for a new one!",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 15.dp),
                color = outlineDark

                )


                // My beloved spacer
            Spacer(modifier = Modifier.height(14.dp))



                // Loading section
            GeminiLoadBar()






        }


    }


}




@Composable
fun GeminiLoadBar(){

    LinearProgressIndicator(
        modifier = Modifier.width(366.dp)
    )


}