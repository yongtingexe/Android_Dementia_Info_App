package com.example.mindspirit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mindspirit.home.HomeScreen
import com.example.mindspirit.invite.ContactScreen
import com.example.mindspirit.quiz.QuizPickerScreen
import com.example.mindspirit.reader.ArticleReaderScreen
import com.example.mindspirit.reading.ReadingPickerScreen
import com.example.mindspirit.search.FindDoctorScreen

@Composable
fun Navigation(
    navController: NavHostController
){
    val pdfUrl = "https://pdfhost.io/v/WyaRl3OIS_linkedinfutureofskills2019apacwebupdated"
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController)
        }
        composable(
            route = Screen.ReadingPicker.route
        ){
            ReadingPickerScreen(navController)
        }
        composable(
            route = Screen.QuizPicker.route
        ){
            QuizPickerScreen()
        }
        composable(
            route = Screen.FindDoctor.route
        ){
            FindDoctorScreen()
        }
        composable(
            route = Screen.WhatsappInvite.route
        ){
            ContactScreen()
        }
        composable(
            route = Screen.WebReader.route
        ){
            ArticleReaderScreen(url = pdfUrl)
        }

    }
 }