package com.example.moviestreaming.data

import java.io.Serializable

data class Item(
    val _id: String,
    val modified: Modified,
    val name: String,
    val origin_name: String,
    val poster_url: String,
    val slug: String,
    val thumb_url: String,
    val year: Int
): Serializable