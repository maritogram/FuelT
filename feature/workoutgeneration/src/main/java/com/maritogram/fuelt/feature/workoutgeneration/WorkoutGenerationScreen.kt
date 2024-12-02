package com.maritogram.fuelt.feature.workoutgeneration


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maritogram.fuelt.core.designsystem.theme.FueltTheme
import com.maritogram.fuelt.core.designsystem.theme.outlineDark
import com.maritogram.fuelt.core.designsystem.theme.secondaryContainerDark
import com.maritogram.fuelt.core.designsystem.theme.secondaryDark
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.maritogram.fuelt.core.designsystem.theme.onSecondaryContainerDark

@Composable
fun WorkoutGenerationScreen(
    onExitClick: () -> Unit
) {

    Scaffold { p ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal))
                .consumeWindowInsets(p)
                .padding(
                    top = p.calculateTopPadding(),
                    bottom = p.calculateBottomPadding(),
                    start = 20.dp,
                    end = 20.dp
                )
        ) {

            Spacer(Modifier.height(39.dp))

            Icon(
                modifier = Modifier.size(52.dp),
                painter = painterResource(com.maritogram.fuelt.feature.workoutgeneration.R.drawable.fitness_center),
                contentDescription = "Dumbbell",
                tint = secondaryDark

            )


            Spacer(Modifier.height(7.dp))
            Text("It's time to workout!", fontSize = 28.sp)
            Spacer(Modifier.height(19.dp))
            Text(
                "Let’s get your routine ready. To manually create workouts you can always go back and select the appropriate tab.",
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp,
                color = outlineDark

            )

            Spacer(Modifier.height(21.dp))
            var selectedIndex by remember { mutableIntStateOf(2) }

            // Work around for now...
            val animatedHeight by animateDpAsState(
                targetValue = if (selectedIndex == 0) 161.dp else  140.dp,
                label = "animatedWidth"
            )

            Card(
                modifier = Modifier
                    .width(378.dp)
                    .height(animatedHeight).animateContentSize(),
                colors = CardDefaults.cardColors(
                    secondaryContainerDark.copy(0.6f)
                )
            ) {


                Spacer(Modifier.height(11.dp))



                FueltSegmentedButton(
                    listOf("Generate", "Existing"), modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    ),
                    selectedIndex = selectedIndex,
                    onSelectedChange = { selectedIndex = it }
                )

                Spacer(Modifier.height(11.dp))


                // Sorry everyone
                if (selectedIndex == 0)
                    Text(
                        "By selecting this option you will be guided through a series of questions to help create your personalized workout.",
                        modifier = Modifier
                            .padding(horizontal = 13.dp),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = .25.sp,
                        color = onSecondaryContainerDark.copy(0.6f)
                    )
                else if (selectedIndex == 1)
                    Text(
                        text = "Choose from your library of existing workouts. Filter by muscle group, workout type, or duration.",
                        modifier = Modifier
                            .padding(horizontal = 13.dp),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = .25.sp,
                        color = onSecondaryContainerDark.copy(0.6f)
                    )
                else{
                    Text(
                        text = "Generate a personalized AI workout or choose from your past workouts.",
                        modifier = Modifier
                            .padding(horizontal = 13.dp),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = .25.sp,
                        color = onSecondaryContainerDark.copy(0.6f)
                    )
                }

            }
        }


    }
}


@Composable
fun FueltSegmentedButton(
    options: List<String>,
    selectedIndex: Int,
    onSelectedChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                onClick = { onSelectedChange(index) },
                selected = index == selectedIndex,
                colors = SegmentedButtonDefaults.colors(inactiveContainerColor = Color.Transparent)
            ) { Text(label) }
        }
    }

}


@Preview
@Composable
fun FueltSegmentedButtonPreview() {
    FueltTheme(darkTheme = true) {
    }
}