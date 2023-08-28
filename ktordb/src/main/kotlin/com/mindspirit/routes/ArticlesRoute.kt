package com.mindspirit.routes

import com.mindspirit.data.model.Article
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private const val BASE_URL = "http://172.16.66.46:8080"
private val articles = listOf(
    Article("What is Dementia", "What is Dementia", "$BASE_URL/thumbnails/whatisdementia.jpg"),
    Article("Ways to Communicate", "Ways to Communicate", "$BASE_URL/thumbnails/howtocommunicate.jpg"),
    Article("Causes & Risks", "Causes & Risks", "$BASE_URL/thumbnails/riskfactor.jpg"),
    Article("Recommended Exercises", "Recommended Exercies", "$BASE_URL/thumbnails/recommendedactivities.jpg")
)

fun Route.articles(){
    get("articles"){
        call.respond(
            HttpStatusCode.OK,
            articles
        )
    }
}