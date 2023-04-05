package com.example.mindspirit.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FindDoctorScreen(){

    val viewModel = viewModel<SearchViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val doctors by viewModel.doctors.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle.Default.copy(fontSize = 18.sp),
            placeholder = {Text(text = "Search")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f))
        { items(doctors){doctor ->
                Text(
                    text = "${doctor.firstName} ${doctor.lastName}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}