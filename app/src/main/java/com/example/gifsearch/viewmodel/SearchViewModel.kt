package com.example.gifsearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gifsearch.data.remote.GiphyPagingSource
import com.example.gifsearch.data.remote.RetrofitInstance
import com.example.gifsearch.data.models.GifObject
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

class SearchViewModel : ViewModel() {

    fun searchGifs(query: String) : Flow<PagingData<GifObject>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = { GiphyPagingSource(RetrofitInstance.api, query) }
        ).flow.cachedIn(viewModelScope)
    }
}