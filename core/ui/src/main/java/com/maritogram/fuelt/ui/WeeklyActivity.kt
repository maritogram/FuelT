package com.maritogram.fuelt.ui

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maritogram.fuelt.core.designsystem.theme.FueltTheme
import com.maritogram.fuelt.core.designsystem.theme.onPrimaryContainerDark
import com.maritogram.fuelt.core.designsystem.theme.onPrimaryDark
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceDark
import com.maritogram.fuelt.core.designsystem.theme.outlineVariantDark
import com.maritogram.fuelt.core.designsystem.theme.surfaceContainerDark


@Composable
fun WeeklyActivity() {
    OutlinedCard(
        border = BorderStroke(1.dp, outlineVariantDark),
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainerDark,
        ), modifier = Modifier
            .height(120.dp)
            .width(360.dp)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Your weekly activity",
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp),
                    fontWeight = FontWeight.Medium
                )

            }

            Row(modifier = Modifier.fillMaxWidth()) {
                // Achieved column
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 5.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    // TODO: Make this change based on weekly activity duh.
                    Text(
                        "0/3",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = onPrimaryContainerDark
                    )
                    Text(
                        text = "Achieved",
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = onPrimaryContainerDark
                    )

                }

                // Days column
                Column(
                    Modifier.fillMaxSize().padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    WeekdayList()

                }


            }
        }

    }


}

@Composable
fun WeekdayList() {
    val days = listOf("S", "M", "T", "W", "T", "F", "S")
    Row(
        modifier = Modifier
            .width(198.dp)
            .height(33.dp)
            .padding(end=10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),


        ) {
//        Spacer(Modifier.weight(1f))
        days.forEach { day ->
            CircleDay(day)
        }
    }
}

@Composable
fun CircleDay(day: String) {
    Column(Modifier.width(13.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        //external circle
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(13.dp),
        ){
            //internal circle with icon
            Box(
                modifier = Modifier
                    .size(12.dp)
                    // TODO: Remember to change color based on activity
                    .background(surfaceContainerDark, CircleShape)
                    .border(.4.dp, onSurfaceDark,CircleShape)
                    .padding(2.dp),
            )
        }

        Text(
            text = day,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
//            color = Color.White
        )
    }
}


@Preview
@Composable
fun WeeklyActivityPreview() {
    FueltTheme(darkTheme = true) {
        Surface {
            WeeklyActivity()
        }
    }
}