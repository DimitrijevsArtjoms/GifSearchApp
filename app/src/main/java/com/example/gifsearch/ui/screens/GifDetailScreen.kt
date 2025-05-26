package com.example.gifsearch.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter




@Composable
fun GifDetailScreen(gifUrl: String, title: String, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize().padding(16.dp)
        )
    {
        Image(
            painter = rememberAsyncImagePainter(gifUrl),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
            )
    }
}