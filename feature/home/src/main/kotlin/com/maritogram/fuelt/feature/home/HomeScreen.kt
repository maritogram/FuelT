package com.maritogram.fuelt.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp


@Composable
internal fun HomeScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .padding(31.dp)
            .fillMaxSize()
            .testTag("bookmarks:empty"),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ){
        Text("test")


        Spacer(modifier= Modifier.height(20.dp))

        Text("Welcome back, \nMario",
            style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier= Modifier.height(13.dp))

        Text("Weeks box" )



    }



}