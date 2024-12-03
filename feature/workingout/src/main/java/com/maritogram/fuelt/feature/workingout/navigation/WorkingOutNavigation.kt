package com.maritogram.fuelt.feature.workingout.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.workingout.WorkingOutScreen
import kotlinx.serialization.Serializable

@Serializable
object WorkingOutRoute


// Navcontroller extension method
fun NavController.navigateToWorkingOutScreen(){
    navigate(WorkingOutRoute)
}


// Navhost extension method
fun NavGraphBuilder.workingOutScreen(){
    composable<WorkingOutRoute>{
        WorkingOutScreen()
    }
}

