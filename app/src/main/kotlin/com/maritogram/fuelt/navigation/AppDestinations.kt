package com.maritogram.fuelt.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.maritogram.fuelt.feature.routines.navigation.RoutinesRoute
import com.maritogram.fuelt.feature.trainer.navigation.TrainerRoute
import kotlinx.serialization.Serializable
import com.maritogram.fuelt.feature.trainer.R as trainerR

import kotlin.reflect.KClass


enum class AppDestinations(
    val route: KClass<*>,
) {
    TRAINER(
        route = TrainerRoute::class
    ),
    ROUTINES(
        route = RoutinesRoute::class
    )


}