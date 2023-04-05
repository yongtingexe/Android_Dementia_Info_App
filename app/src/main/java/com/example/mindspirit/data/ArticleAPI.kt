package com.example.mindspirit.data

import retrofit2.http.GET

interface ArticleAPI {
    @GET("/articles")
    suspend fun getArticles(): List<Article>

    companion object{
        const val BASE_URL = "http:192.168.0.11:8080"
    }
}