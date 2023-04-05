package com.example.mindspirit.home

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadFromTo(from: Offset, to: Offset){
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + from.y) / 2f,
        abs(from.y + to.y) / 2f
    )
}