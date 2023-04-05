package com.example.mindspirit

sealed class Screen(val route: String){
    object Home: Screen(route = "home_screen")
    object ReadingPicker: Screen(route = "reading_picker_screen")
    object QuizPicker: Screen(route = "quiz_picker_screen")
    object WhatsappInvite: Screen(route = "whatsapp_invite_screen")
    object FindDoctor: Screen(route = "find_doctor_screen")
    object WebReader: Screen(route = "web_reader_screen")
}
