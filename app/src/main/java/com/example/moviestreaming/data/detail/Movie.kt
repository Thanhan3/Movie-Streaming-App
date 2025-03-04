package com.example.moviestreaming.data.detail

import java.io.Serializable

data class Movie(
    val _id: String,
    val actor: List<String>,
    val category: List<Category>,
    val chieurap: Boolean,
    val content: String,
    val country: List<Country>,
    val created: Created,
    val director: List<String>,
    val episode_current: String,
    val episode_total: String,
    val imdb: Imdb,
    val is_copyright: Boolean,
    val lang: String,
    val modified: Modified,
    val name: String,
    val notify: String,
    val origin_name: String,
    val poster_url: String,
    val quality: String,
    val showtimes: String,
    val slug: String,
    val status: String,
    val sub_docquyen: Boolean,
    val thumb_url: String,
    val time: String,
    val tmdb: Tmdb,
    val trailer_url: String,
    val type: String,
    val view: Int,
    val year: Int
): Serializable