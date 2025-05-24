package com.example.gifsearch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import retrofit2.http.Query


@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit) {
    TextField(value = query,
        onValueChange = {onQueryChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search gif...")},
        singleLine = true
        )
}