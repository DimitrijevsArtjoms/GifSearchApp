package com.example.gifsearch.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.gifsearch.data.models.GifObject
import com.example.gifsearch.viewmodel.SearchViewModel
import retrofit2.http.Query

@Composable
fun GifGrid(
    searchQuery: String,
    onGifClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel()
) {
    val gifs: LazyPagingItems<GifObject> = viewModel.searchGifs(searchQuery).collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Adaptive(150.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gifs.itemCount) { index ->
                val gif = gifs[index]
                gif?.let {
                    Image(painter = rememberAsyncImagePainter(model = it.images.preview_gif.url),
                        contentDescription = it.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable { onGifClick(it.id) }
                        )
                }
            }

            if (gifs.loadState.append is LoadState.Loading) {
                item(span = {GridItemSpan(maxLineSpan)} ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }

        if (gifs.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (gifs.loadState.refresh is LoadState.Error) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                Text("Loading error", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
