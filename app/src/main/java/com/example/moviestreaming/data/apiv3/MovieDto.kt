package com.example.moviestreaming.data.apiv3

data class MovieDto(
    val items: List<Item>,
    val pagination: Pagination,
    val status: Boolean
)