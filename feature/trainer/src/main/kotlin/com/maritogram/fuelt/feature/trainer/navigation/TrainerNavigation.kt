package com.maritogram.fuelt.feature.trainer.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.maritogram.fuelt.feature.trainer.TrainerScreen
import kotlinx.serialization.Serializable


@Serializable object TrainerRoute

fun NavController.navigateToTrainer(navOptions: NavOptions) =
    navigate(route = TrainerRoute, navOptions)

fun NavGraphBuilder.trainerScreen(

){
    composable<TrainerRoute> {
        TrainerScreen()
    }
}










