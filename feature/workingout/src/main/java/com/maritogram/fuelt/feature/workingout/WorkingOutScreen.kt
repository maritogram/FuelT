package com.maritogram.fuelt.feature.workingout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maritogram.fuelt.core.designsystem.theme.FueltTheme
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceVariantDark
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceVariantLight
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.maritogram.fuelt.core.designsystem.theme.tertiaryContainerDark
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds


@Composable
fun WorkingOutScreen(
    //TODO: Add list of routine objects
    viewModel: WorkingOutViewModel = hiltViewModel()


) {
    // Timer state, used by the pause fab, and the timer itself.

    val state = viewModel.state.collectAsState()
    LaunchedEffect(key1 = true, block = { viewModel.startTimer() })

    Scaffold(
        topBar = {
            WorkingOutTopAppBar()
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
            modifier = Modifier.padding(
                top = p.calculateTopPadding(),
                bottom = p.calculateBottomPadding()
            )
        ) {


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CountDown(state.value.currentTime)
            }


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
        horizontalAlignment = Alignment.CenterHorizontally
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
fun WorkingOutTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        title = {
            IconButton({}) {
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


@Preview
@Composable
fun WorkingOutScreenPreview() {
    FueltTheme(darkTheme = true) {
        WorkingOutScreen()
    }
}