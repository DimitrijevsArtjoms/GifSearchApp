package com.example.gifsearch.data.models

data class GiphyResponse(
    val data: List<GifObject>,
    val pagination: Pagination
)

data class GifObject(
    val id: String,
    val title: String,
    val images: Images
)

data class Images(
    val original: ImageInfo,
    val preview_gif: ImageInfo
)

data class ImageInfo(
    val url: String
)

data class Pagination(
    val total_count: Int,
    val count: Int,
    val offset: Int
)