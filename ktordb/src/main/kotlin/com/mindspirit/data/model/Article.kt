package com.mindspirit.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val description: String,
    val thumbnailUrl: String
)
