package com.maritogram.fuelt.feature.workoutgeneration


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maritogram.fuelt.core.designsystem.theme.onSecondaryContainerDark
import com.maritogram.fuelt.core.designsystem.theme.outlineVariantDark

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
            // Going to need some state to change the behaviour of what the next button does.
            var nextButtonDestination by remember { mutableStateOf("SecondAI") }
            val workoutGenController = rememberNavController()
            var heightForCard by remember { mutableStateOf(140.dp) }

            Column {
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


                // Work around for now...
                val animatedHeight by animateDpAsState(
                    targetValue = heightForCard,
                    label = "animatedWidth"
                )



                Card(
                    modifier = Modifier
                        .width(378.dp)
                        .height(animatedHeight)
                        .animateContentSize(),
                    colors = CardDefaults.cardColors(
                        secondaryContainerDark.copy(0.6f)
                    )
                ) {

                    // I know.. I know..
                    NavHost(
                        navController = workoutGenController,
                        startDestination = "First"
                    ) {
                        composable("First") {
                            TwoChoiceScreen(
                                { heightForCard = it }
                            )
                        }

                        composable("SecondAI") {
                            Column {
                                heightForCard = 500.dp
                                repeat(50) {
                                    Text(it.toString())
                                }
                            }


                        }


                    }
                }
            }


            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                HorizontalDivider(
                    Modifier
                        .fillMaxWidth()
                        // This is to surpass the padding https://stackoverflow.com/questions/71676749/how-to-make-divider-ignore-padding-of-parent-in-jetpack-compose
                        .layout() { measurable, constraints ->
                            val placeable = measurable.measure(
                                constraints.copy(
                                    maxWidth = constraints.maxWidth + 42.dp.roundToPx(), //add the end padding 16.dp
                                )
                            )
                            layout(placeable.width, placeable.height) {
                                placeable.place(0.dp.roundToPx(), 0)
                            }
                        }, color = outlineVariantDark
                )
                Spacer(Modifier.height(6.dp))
                Button(
                    onClick = { workoutGenController.navigate(nextButtonDestination) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(secondaryDark)
                ) { Text("Next") }

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


@Composable
fun TwoChoiceScreen(
    onHeightChange: (Dp) -> Unit
) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(2) }

    Column(modifier = Modifier.fillMaxSize()) {
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
        if (selectedIndex == 0){
            onHeightChange(161.dp)
            Text(
                "By selecting this option you will be guided through a series of questions to help create your personalized workout.",
                modifier = Modifier
                    .padding(horizontal = 13.dp),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = .25.sp,
                color = onSecondaryContainerDark.copy(0.6f)
            )
        }

        else if (selectedIndex == 1){
            onHeightChange(140.dp)

            Text(
                text = "Choose from your library of existing workouts. Filter by muscle group, workout type, or duration.",
                modifier = Modifier
                    .padding(horizontal = 13.dp),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = .25.sp,
                color = onSecondaryContainerDark.copy(0.6f)
            )
        }
        else {
            onHeightChange(140.dp)
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


@Preview
@Composable
fun FueltSegmentedButtonPreview() {
    FueltTheme(darkTheme = true) {
    }
}