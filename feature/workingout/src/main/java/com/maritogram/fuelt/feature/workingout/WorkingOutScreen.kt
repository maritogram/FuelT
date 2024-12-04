package com.maritogram.fuelt.feature.workingout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceVariantDark
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceVariantLight
import com.maritogram.fuelt.core.designsystem.theme.outlineDark
import com.maritogram.fuelt.core.designsystem.theme.outlineVariantDark
import com.maritogram.fuelt.core.designsystem.theme.surfaceContainerDark
import com.maritogram.fuelt.core.designsystem.theme.tertiaryContainerDark
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
                Text("Back Squat")
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
                    .padding(end=8.dp, top=1.dp)
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
        modifier = Modifier.heightIn(870.dp),
        dragHandle = {},
    ) {
        // Sheet content

        Text("This is ${test}")

    }


}


@Composable
fun CountDown(currentTime: Int) {

    val f: NumberFormat = DecimalFormat("00")

    val formattedElapsedTime = (currentTime.seconds.toComponents { hours, minutes, seconds, _ ->
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

