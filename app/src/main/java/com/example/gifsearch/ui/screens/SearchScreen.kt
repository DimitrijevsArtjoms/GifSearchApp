package com.example.gifsearch.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.gifsearch.ui.components.GifGrid
import com.example.gifsearch.ui.components.SearchBar


@Composable
fun SearchScreen(
    onGifClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var query by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(8.dp)) {
        SearchBar(
            query = query, onQueryChanged = { query = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        GifGrid(searchQuery = query, onGifClick = onGifClick)
    }
}