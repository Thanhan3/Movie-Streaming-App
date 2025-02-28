package com.example.moviestreaming.data

import java.io.Serializable

data class FilmDto(
    val items: List<Item>,
    val pagination: Pagination,
    val status: Boolean
): Serializable