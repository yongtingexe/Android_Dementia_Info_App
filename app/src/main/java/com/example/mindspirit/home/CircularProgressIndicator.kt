package com.example.mindspirit.home
import android.graphics.Paint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindspirit.ui.theme.*
import kotlin.math.*

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    initialValue: Float,
    primaryColor: androidx.compose.ui.graphics.Color,
    secondaryColor: androidx.compose.ui.graphics.Color,
    minValue: Int = 0,
    maxValue: Int = 100,
    circleRadius: Float
){
    var animationPlayed by remember{
        mutableStateOf(false)
    }

    val currPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) initialValue else 0f,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 500
        )
    )

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    var circleCenter by remember{
        mutableStateOf(Offset.Zero)
    }

    val positionValue by remember{
        mutableStateOf(initialValue)
    }

    Box(
        modifier = Modifier
    ){
        Canvas(
            modifier = Modifier
                .size(350.dp)
                .padding(75.dp)
        ){
            val width = size.width
            val height = size.height
            val circleThickness = width/15f
            circleCenter = Offset(x = width/2f, y = height/2f)

            drawCircle(
                brush = Brush.radialGradient(
                    listOf(
                        primaryColor.copy(alpha = 0.45f),
                        secondaryColor.copy(alpha = 0.15f)
                    )
                ),
                radius = circleRadius,
                center = circleCenter
            )

            drawCircle(
                style = Stroke(
                    width = circleThickness
                ),
                color = secondaryColor,
                radius = circleRadius,
                center = circleCenter
            )

            drawArc(
                color = primaryColor,
                startAngle = 90f,
                sweepAngle =  (360f/maxValue) * positionValue,
                style = Stroke(
                    width = circleThickness,
                    cap = StrokeCap.Round
                ),
                useCenter = false,
                size = Size(
                    width = circleRadius * 2f,
                    height = circleRadius * 2f
                ),
                topLeft = Offset(
                    (width - circleRadius * 2f)/2f,
                    (height - circleRadius * 2f)/2f
                )
            )

            val outerRadius = circleRadius + circleThickness/2f
            val gap = 15f
            for (i in 0..(maxValue-minValue)){
                val color = if(i < positionValue - minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
                val angleInDeg = i * 360/(maxValue - minValue).toFloat()
                val angleInRad = angleInDeg * PI/180f + PI/2f

                val yGapAdjustment = cos(angleInDeg * PI/180f) * gap
                val xGapAdjustment = -sin(angleInDeg * PI/180f) * gap

                val start = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
                )

                val end = Offset(
                    x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                    y = (outerRadius * sin(angleInRad) + circleThickness + circleCenter.y + yGapAdjustment).toFloat()
                )

                rotate(
                    angleInDeg,
                    pivot = start
                ){
                    drawLine(
                        color = color,
                        start = start,
                        end = end,
                        strokeWidth = 2.5.dp.toPx()
                    )
                }
            }

            drawContext.canvas.nativeCanvas.apply {
                drawIntoCanvas {
                    drawText(
                        "$positionValue %",
                        circleCenter.x,
                        circleCenter.y + 45.dp.toPx()/3f,
                        Paint().apply {
                            textSize = 38.sp.toPx()
                            textAlign = Paint.Align.CENTER
                            color = black.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }

}