package com.example.gifsearch.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gifsearch.data.remote.GiphyApiService
import com.example.gifsearch.data.models.GifObject
import com.example.gifsearch.utils.Constants

class GiphyPagingSource(private val api: GiphyApiService, private val query: String) : PagingSource<Int, GifObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifObject> {
        return try {
            val offset = params.key ?: 0
            val response = if (query.isEmpty()) {
                api.getTrendingsGifs(Constants.GIPHY_API_KEY, offset, Constants.PAGE_SIZE)
            }
            else {
                api.searchGifs(Constants.GIPHY_API_KEY, query, offset, Constants.PAGE_SIZE)
            }

            val gifs = response.data

            LoadResult.Page(
                data = gifs,
                prevKey = if (offset == 0) null else - Constants.PAGE_SIZE,
                nextKey = if (gifs.isEmpty()) null else offset + Constants.PAGE_SIZE

            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifObject>): Int? {
        return state.anchorPosition
    }
}


