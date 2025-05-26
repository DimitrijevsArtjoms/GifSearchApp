package com.example.gifsearch.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter


@Composable
fun GifDetailScreen(gifUrl: String, title: String, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier.fillMaxSize().padding(16.dp)
        )
    {
        Image(painter.rememberAsyncImagePainter(gifUrl),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
            )
    }
}