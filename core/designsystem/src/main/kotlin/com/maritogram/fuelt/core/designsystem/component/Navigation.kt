package com.maritogram.fuelt.core.designsystem.component

import android.content.Intent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun RowScope.FueltNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: @Composable (()-> Unit)? = null
    //TODO: Fill out later
){
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        label = label
    )
}


@Composable
fun FueltNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = 0.dp,
        content = content,
    )
}


@Preview
@Composable
fun FueltNavigationBarPreview(){

    var selectedItem by remember { mutableIntStateOf(0) }

    // Test items
    val items = listOf("Trainer", "Workouts", "NotSure")

    val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Favorite, Icons.Filled.Star)
    val unselectedIcons =
        listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder, Icons.Outlined.Star)

    FueltNavigationBar {
        items.forEachIndexed{
            index: Int, item: String ->
            FueltNavigationBarItem(
                icon = {
                    Icon(
                        if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )


        }


    }

}