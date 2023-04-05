package com.example.mindspirit.home

import com.example.mindspirit.Screen
import com.example.mindspirit.ui.theme.*

val options = listOf(
    Option(
        title = "Reading",
        BlueViolet1,
        BlueViolet2,
        BlueViolet3,
        route = Screen.ReadingPicker.route
    ),
    Option(
        title = "Quiz",
        LightGreen1,
        LightGreen2,
        LightGreen3,
        route = Screen.QuizPicker.route
    ),
    Option(
        title = "Invite Friends",
        OrangeYellow1,
        OrangeYellow2,
        OrangeYellow3,
        route = Screen.WhatsappInvite.route
    ),
    Option(
        title = "Find Doctors",
        Beige1,
        Beige2,
        Beige3,
        route = Screen.FindDoctor.route
    )
)