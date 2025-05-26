package com.example.gifsearch.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gifsearch.data.models.GifObject
import com.example.gifsearch.utils.Constants

class GiphyPagingSource(private val api: GiphyApiService, private val query: String) : PagingSource<Int, GifObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifObject> {
        return try {
            val offset = params.key ?: 0
            val response = if (query.isEmpty()) {
                api.getTrendingsGifs(Constants.GIPHY_API_KEY, Constants.PAGE_SIZE, offset)
            }
            else {
                api.searchGifs(Constants.GIPHY_API_KEY, query, Constants.PAGE_SIZE, offset)
            }

            val gifs = response.data

            LoadResult.Page(
                data = gifs,
                prevKey = if (offset == 0) null else offset - Constants.PAGE_SIZE,
                nextKey = if (gifs.isEmpty()) null else offset + Constants.PAGE_SIZE
            )
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifObject>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(Constants.PAGE_SIZE)
                ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(Constants.PAGE_SIZE)
        }
    }
}


