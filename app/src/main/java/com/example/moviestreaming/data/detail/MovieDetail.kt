package com.example.moviestreaming.data.detail

import java.io.Serializable

data class MovieDetail(
    val episodes: List<Episode>,
    val movie: Movie,
    val msg: String,
    val status: Boolean
): Serializable