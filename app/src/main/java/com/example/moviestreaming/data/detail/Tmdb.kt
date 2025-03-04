package com.example.moviestreaming.data.detail

import java.io.Serializable

data class Tmdb(
    val id: Any,
    val season: Any,
    val type: Any,
    val vote_average: Float,
    val vote_count: Int
): Serializable