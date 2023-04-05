package com.example.mindspirit.reading

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.mindspirit.Screen
import com.example.mindspirit.data.Article
import com.example.mindspirit.ui.theme.*


@Composable
fun ReadingPickerScreen(navController: NavController){

    val viewModel: MainViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LazyColumn(){
        if(state.isEmpty()){
            item{
                androidx.compose.material.CircularProgressIndicator()
            }
        }

        items(state){article: Article ->
            ImageCard(article = article, navController = navController)
            Spacer(modifier = Modifier.height(25.dp))
        }
    }

}


@Composable
fun ImageCard(article: Article, navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.WebReader.route) },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(275.dp)){
            Image(
                rememberImagePainter(data = article.thumbnailUrl),
                contentDescription = article.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(article.title, style = TextStyle(color = TextWhite), fontSize = 16.sp)
            }

        }
    }
}