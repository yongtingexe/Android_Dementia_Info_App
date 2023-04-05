package com.example.mindspirit.reading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindspirit.data.Article
import com.example.mindspirit.data.ArticleRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articleRepo: ArticleRepo
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Article>())
    val state: StateFlow<List<Article>>
        get() = _state


    init {
        viewModelScope.launch {
            val articles = articleRepo.getArticlesList()
            _state.value = articles
        }
    }

}