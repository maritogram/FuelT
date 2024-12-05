package com.maritogram.fuelt.feature.workingout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maritogram.fuelt.core.designsystem.theme.errorDark
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceVariantDark
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceVariantLight
import com.maritogram.fuelt.core.designsystem.theme.outlineDark
import com.maritogram.fuelt.core.designsystem.theme.outlineVariantDark
import com.maritogram.fuelt.core.designsystem.theme.secondaryDark
import com.maritogram.fuelt.core.designsystem.theme.surfaceContainerDark
import com.maritogram.fuelt.core.designsystem.theme.tertiaryContainerDark
import com.maritogram.fuelt.core.designsystem.theme.tertiaryDark
import kotlinx.coroutines.CoroutineScope
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkingOutScreen(
    //TODO: Add list of routine objects
    viewModel: WorkingOutViewModel = hiltViewModel(),
    onExitClick: () -> Unit,


    ) {
    // Timer state, used by the pause fab, and the timer itself.

    // TODO: ALERT DIALOG WHEN TRYING TO LEAVE
//
//    var showAlertDialog by remember { mutableStateOf(false)}
//
////    BackHandler {
////        showAlertDialog = true
////    }
////
////    if(showAlertDialog){
////        BasicAlertDialog({}){
////            Text("HEY")
////        }
////    }

    val state = viewModel.state.collectAsState()
    LaunchedEffect(key1 = true, block = { viewModel.startTimer() })

    Scaffold(
        topBar = {
            WorkingOutTopAppBar(onExitClick)
        },
        floatingActionButton = {
            DoubleFAB(
                viewModel::stopTimer,
                viewModel::startTimer,
                {},
                state.value.isTimerGoing
            )
        },


        ) { p ->
        Column(
            modifier = Modifier
                .padding(
                    top = p.calculateTopPadding(),
                    bottom = p.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
                .wrapContentHeight(),

            ) {


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CountDown(state.value.currentTime)
            }

            Spacer(Modifier.height(37.dp))

            ExerciseBlocks()

            Spacer(Modifier.height(195.dp))


        }


    }


}


@Composable
fun DoubleFAB(
    onPauseClick: () -> Unit,
    onResumeClick: () -> Unit,
    onStopClick: () -> Unit,
    status: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FloatingActionButton(
            containerColor = tertiaryContainerDark,
            modifier = Modifier.padding(bottom = 13.dp),
            onClick = {
                if (status)

                    onPauseClick()
                else
                    onResumeClick()

            }) {

            if (status) {
                Icon(painter = painterResource(R.drawable.pauseicon), "")
            } else {
                Icon(imageVector = Icons.Default.PlayArrow, "")
            }

        }

        LargeFloatingActionButton(onStopClick) {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(R.drawable.stopicon),
                contentDescription = "Stop"
            )
        }


    }


}


@Composable
fun ExerciseBlocks() {


    // Create top lines


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        // Display 10 items
        val pagerState = rememberPagerState(pageCount = {
            3
        })

        Row(
            Modifier
                .width(366.dp)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            HorizontalDivider(
                modifier = Modifier.width(100.dp),
                thickness = 2.dp,
                color = if (pagerState.currentPage == 0) outlineDark else outlineVariantDark
            )
            Spacer(modifier = Modifier.width(31.dp))
            HorizontalDivider(
                modifier = Modifier.width(100.dp),
                thickness = 2.dp,
                color = if (pagerState.currentPage == 1) outlineDark else outlineVariantDark
            )
            Spacer(modifier = Modifier.width(31.dp))
            HorizontalDivider(
                modifier = Modifier.width(100.dp),
                thickness = 2.dp,
                color = if (pagerState.currentPage == 2) outlineDark else outlineVariantDark
            )
        }




        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) { page ->
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {// Our page content
                Text(
                    text = "Block $page",
                    modifier = Modifier.fillMaxWidth(),
                    letterSpacing = 0.sp,
                    lineHeight = 36.sp,
                    fontSize = 28.sp

                )

                Spacer(Modifier.height(21.dp))


                ExerciseSegment(ExerciseSegments.FIRSTSEGMENT)
                for (i in 1..page) {
                    ExerciseSegment(test = i)
                }
                ExerciseSegment(ExerciseSegments.FINALSEGMENT)


            }
        }
    }


    // Create block


}

enum class ExerciseSegments {
    FIRSTSEGMENT,
    FINALSEGMENT,
}


@Composable
fun ExerciseSegment(
    segment: ExerciseSegments? = null,
    test: Int? = null
) {

    if (segment == ExerciseSegments.FIRSTSEGMENT) {
        ExerciseSegmentCard()

    } else if (segment == ExerciseSegments.FINALSEGMENT) {
        VerticalDivider(
            Modifier
                .height(57.dp)
                .offset(x = 41.dp),
            color = outlineVariantDark.copy(0.40f)
        )
        ExerciseSegmentCard(test)

    } else {
        VerticalDivider(
            Modifier
                .height(57.dp)
                .offset(x = 41.dp),
            color = outlineVariantDark.copy(0.40f)
        )
        ExerciseSegmentCard(test)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseSegmentCard(
    test: Int? = null
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .width(361.dp)
            .height(110.dp)
            .clickable {
                showBottomSheet = true
            },
        colors = CardDefaults.cardColors(surfaceContainerDark),
        border = BorderStroke(1.dp, color = outlineVariantDark)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.placeholderimage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(79.dp)
                    .fillMaxHeight()
            )

            Column(modifier = Modifier.padding(start = 13.dp)) {
                Text(
                    text = "Back Squat",
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "5 Sets • 8 Reps • 5 lb ",
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    fontSize = 14.sp

                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 8.dp, top = 1.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrowright),
                    "",
                    tint = onSurfaceVariantLight,
                    modifier = Modifier
                        .padding()
                        .width(20.dp)
                        .height(40.dp)
                )
            }

            if (showBottomSheet) {
                ExerciseBottomSheet(
                    sheetState = sheetState,
                    scope = scope,
                    showBottomSheet = { showBottomSheet = it },
                    test = test
                )
            }


        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseBottomSheet(
    sheetState: SheetState,
    scope: CoroutineScope,
    showBottomSheet: (Boolean) -> Unit,
    test: Int?
) {


    ModalBottomSheet(
        onDismissRequest = {
            showBottomSheet(false)
        },
        sheetState = sheetState,
        properties = ModalBottomSheetProperties(),
        modifier = Modifier
            .heightIn(870.dp),
        dragHandle = {},
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholderimage),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(
                    compositingStrategy =
                    CompositingStrategy.Offscreen
                )
                .drawWithContent {
                    val brush = Brush.verticalGradient(0.7f to Color.Red, 1f to Color.Transparent)
                    drawContent()
                    drawRect(brush = brush, blendMode = BlendMode.DstIn)
                }
                .height(204.dp)
        )

        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            //Sheet content is already "inside" a column.

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Back Squat",
                    lineHeight = 40.sp,
                    letterSpacing = 0.sp,
                    fontSize = 32.sp,
                    modifier = Modifier.widthIn(max = 250.dp)
                )
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(7.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = secondaryDark),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = Modifier
                        .defaultMinSize(minWidth = 90.dp, minHeight = 31.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = ""
                    )

                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Example",
                        lineHeight = 20.sp,
                        letterSpacing = .1.sp,
                        fontSize = 14.sp
                    )
                }


            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button({}) { }
                Button({}) { }

            }

            Spacer(Modifier.height(31.dp))

            // Focusable things.
            SetsAndReps()


        }


    }


}

@Composable
fun SetsAndReps() {


    val focusRequester = remember { FocusRequester() }

    val localFocus = LocalFocusManager.current


    LazyColumn {
        items(5) { i ->

            var showDetails by remember { mutableStateOf(false) }
            val focusRequester = remember { FocusRequester() }
            var circleColor by remember { mutableStateOf(Color.Transparent) }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        when {
                            it.isFocused or it.hasFocus -> {
                                showDetails = true
                                circleColor = tertiaryDark
                            }
                            else -> {
                                showDetails = false
                                circleColor = Color.Transparent
                            }
                        }
                    }
                    .focusable(true)
                    .focusRequester(focusRequester)
                    .clickable {
                        focusRequester.requestFocus()
                    },
                verticalAlignment = Alignment.CenterVertically

            ) {


                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .background(circleColor, CircleShape)
                        .border(width = 1.dp, color = tertiaryDark, CircleShape)
                        .focusable()
                ) {

                    if (i != 4) {
                        androidx.compose.foundation.Canvas(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(2.dp)
                                .offset(x = 13.5.dp, y = 26.dp) // Adjust offset as needed
                        ) {
                            drawLine(
                                color = tertiaryDark,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height + 80f) // Extend line beyond the Row
                            )
                        }
                    }

                }

                Spacer(Modifier.width(17.dp))

                var reps by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = reps,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction =
                        ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            localFocus.moveFocus(FocusDirection.Previous)
                        }),
                    onValueChange = {
                        if (it.all { char -> char.isDigit() } && it.length <= 3) {
                            reps = it
                        }
                    },
                    label = { Text("Reps") },
                    modifier = Modifier
                        .width(150.dp)

                )

                Spacer(Modifier.width(17.dp))

                var weight by remember { mutableIntStateOf(0) }
                OutlinedTextField(
                    value = weight.toString(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction =
                        ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            localFocus.moveFocus(FocusDirection.Previous)
                        }),
                    onValueChange = {
                        if (it.isEmpty()) {
                            weight = 0
                        } else if (it.all { char -> char.isDigit() } && it.length <= 3) {
                            weight = it.toIntOrNull() ?: 0
                        }
                    },
                    label = { Text("Weight (lb)") },
                    modifier = Modifier
                        .width(150.dp)

                )


            }

            if (showDetails) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 19.dp)
                ) {
                    Spacer(Modifier.width(25.dp))

                    if (i != 4) {
                        androidx.compose.foundation.Canvas(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(2.dp)
                                .offset(x = (-29.7).dp, y = 37.2.dp) // Adjust offset as needed
                        ) {
                            drawLine(
                                color = tertiaryDark,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height + 79f) // Extend line beyond the Row
                            )
                        }
                    }


                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(7.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = tertiaryDark),
                        contentPadding = PaddingValues(horizontal = 2.dp),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 275.dp, minHeight = 31.dp)
                    ) {
                        Text("Mark as completed ")
                    }

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(7.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = errorDark),
                        contentPadding = PaddingValues(horizontal = 2.dp),
                        modifier = Modifier
                            .defaultMinSize(minWidth = 31.dp, minHeight = 31.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = ""
                        )
                    }
                }
            } else {
                Spacer(Modifier.height(17.dp))

            }


        }


    }


}


@Composable
fun CountDown(currentTime: Int) {

    val f: NumberFormat = DecimalFormat("00")

    val formattedElapsedTime = (
            currentTime.seconds.toComponents { hours, minutes, seconds, _ ->
                "${f.format(hours)}:${
                    f.format(minutes)
                }:${f.format(seconds)}"
            })

    Text(
        text = formattedElapsedTime,
        textAlign = TextAlign.Center,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
        fontSize = 45.sp
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkingOutTopAppBar(onExitClick: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        title = {
            IconButton(onExitClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close workout",
                    modifier = Modifier.size(36.dp),
                    tint = onSurfaceVariantDark
                )
            }
        }
    )


}

// https://stackoverflow.com/questions/74235464/jetpack-compose-make-launchedeffect-keep-running-while-app-is-running-in-the-b
data class WorkingOutScreenState(
    val currentTime: Int = 0,
    val isTimerGoing: Boolean = false
)

