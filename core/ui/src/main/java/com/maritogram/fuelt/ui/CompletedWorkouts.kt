package com.maritogram.fuelt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maritogram.fuelt.core.designsystem.theme.FueltTheme
import com.maritogram.fuelt.core.designsystem.theme.onSurfaceDark
import com.maritogram.fuelt.core.designsystem.theme.surfaceDark

@Composable
fun NoCompletedWorkouts(
) {
    Column(Modifier.padding(top = 24.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                "(ᇂ_Jᇂ )",
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp,
                color = onSurfaceDark.copy(alpha = 0.6f)
            )

        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                "No workouts have yet been completed",
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = onSurfaceDark.copy(alpha = 0.4f)
            )

        }
    }


}


@Composable
@Preview
fun NoCompletedWorkoutsPreview() {
    FueltTheme {
        Surface(color = surfaceDark) {
            NoCompletedWorkouts()
        }
    }
}
