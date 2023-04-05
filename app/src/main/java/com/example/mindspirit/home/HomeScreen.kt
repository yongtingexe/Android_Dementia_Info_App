package com.example.mindspirit.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mindspirit.Screen
import com.example.mindspirit.ui.theme.*

@Composable
fun HomeScreen(navController: NavHostController){
        Column(
          modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                initialValue = 76.toFloat(),
                primaryColor = Pink,
                secondaryColor = gray,
                circleRadius = 275f
            )

            OptionMenu(options = options, navController)
        }
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionMenu(options: List<Option>, navController : NavHostController){

    Column(modifier = Modifier.fillMaxWidth()){
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight(),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp)
        ){
            items(options.size){
                OptionItem(option = options[it], navController)
            }
        }
    }
}

@Composable
fun OptionItem(
    option: Option,
    navController: NavHostController
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(option.darkColor)
            .clickable(onClick = {navController.navigate(option.route)})
    ){
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColoredPoint1 = Offset(0f, height*0.3f)
        val mediumColoredPoint2 = Offset(0.1f, height*0.35f)
        val mediumColoredPoint3 = Offset(0.4f, height*0.05f)
        val mediumColoredPoint4 = Offset(0.75f, height*0.7f)
        val mediumColoredPoint5 = Offset(1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply{
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        val lightColoredPoint1 = Offset(0f, height*0.3f)
        val lightColoredPoint2 = Offset(0.1f, height*0.35f)
        val lightColoredPoint3 = Offset(0.4f, height*0.05f)
        val lightColoredPoint4 = Offset(0.75f, height*0.7f)
        val lightColoredPoint5 = Offset(1.4f, -height.toFloat())

        val lightColoredPath = Path().apply{
            moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
            standardQuadFromTo(lightColoredPoint1, lightColoredPoint2)
            standardQuadFromTo(lightColoredPoint2, lightColoredPoint3)
            standardQuadFromTo(lightColoredPoint3, lightColoredPoint4)
            standardQuadFromTo(lightColoredPoint4, lightColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            drawPath(
                path = mediumColoredPath,
                color = option.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = option.lightColor
            )
        }
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
            Text(
                text = option.title,
                fontSize = 35.sp,
                style = MaterialTheme.typography.h2,
                lineHeight = 50.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
