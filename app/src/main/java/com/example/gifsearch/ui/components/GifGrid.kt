package com.example.gifsearch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import retrofit2.http.Query

@Composable
fun GifGrid(
    searchQuery: String,
    onGifClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Search: $searchQuery")
    }
}
