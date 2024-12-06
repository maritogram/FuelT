package com.maritogram.fuelt.core.model

import java.util.Collections

data class exercise(
    val name: String,
    val completionDate: String? = null,
    val sets: Int,
    val reps: ArrayList<Int>,
    val weight: ArrayList<Int>,
    val completedReps: ArrayList<Boolean> = ArrayList<Boolean>(Collections.nCopies<Boolean>(reps.size, false)),
    val image: String? = null,
    val videoLink: String? = null

)