package com.maritogram.fuelt.core.model

import java.time.LocalDate

data class workout(
    val completionDate: LocalDate = LocalDate.now(),
    val duration: Int,
    val AIgenerated: Boolean,
    //TODO: Add exercises so people can see them!

)
