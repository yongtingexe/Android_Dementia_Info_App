package com.example.mindspirit.data

import javax.inject.Inject

class ArticleRepo @Inject constructor(
    private val articleAPI: ArticleAPI
){
    suspend fun getArticlesList(): List<Article>{
        return articleAPI.getArticles()
    }
}