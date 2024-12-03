package com.maritogram.fuelt.feature.workingout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LifecycleResumeEffect
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import java.text.NumberFormat
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds


@Composable
fun WorkingOutScreen(
    //TODO: Add list of routine objects


) {
    Scaffold(
        topBar = {
            WorkingOutTopAppBar()
        },
        floatingActionButton = {
            Column {

            }

        },


        ) { p ->
        Column(
            modifier = Modifier.padding(
                top = p.calculateTopPadding(),
                bottom = p.calculateBottomPadding()
            )
        ) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CountDown()

            }


        }


    }


}


@Composable
fun CountDown() {

    val startTime = System.currentTimeMillis()

    var elapsedTime by remember {
        mutableLongStateOf(0)
    }

    val f: NumberFormat = DecimalFormat("00")

    val formattedElapsedTime = (elapsedTime.seconds.toComponents { hours, minutes, seconds, _ ->
        "${f.format(hours)}:${
            f.format(minutes)
        }:${f.format(seconds)}"
    })

    var isRunning by remember { mutableStateOf(true) }

    LifecycleResumeEffect(Unit) {
        isRunning = true
        onPauseOrDispose { isRunning = false }
    }

    LaunchedEffect(isRunning) {
        while (isRunning) {
             elapsedTime  = (System.currentTimeMillis() - startTime) / 1000
            delay(1000)
        }
    }



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


@Preview
@Composable
fun WorkingOutScreenPreview() {
    FueltTheme(darkTheme = true) {
        WorkingOutScreen()
    }
}