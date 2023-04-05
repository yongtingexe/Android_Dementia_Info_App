package com.example.mindspirit.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class SearchViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _doctors = MutableStateFlow(allDoctors)
    val doctors = searchText
        .combine(_doctors){text, doctors ->
            if(text.isBlank()){
                doctors
            }else{
                doctors.filter {
                    it.doesMatch(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _doctors.value
        )

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }
}

data class Doctor(
    val firstName: String,
    val lastName: String
){
    fun doesMatch(query: String): Boolean{
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}"
        )
        return matchingCombinations.any{
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allDoctors = listOf(
    Doctor(
        firstName = "Johnny",
        lastName = "Inns"
    ),
    Doctor(
        firstName = "Gopal",
        lastName = "P Singh"
    ),
    Doctor(
        firstName = "De",
        lastName = "Juan"
    ),
    Doctor(
        firstName = "Sum Ting",
        lastName = "Wong"
    ),
    Doctor(
        firstName = "Bong",
        lastName = "Saiful"
    )
)
