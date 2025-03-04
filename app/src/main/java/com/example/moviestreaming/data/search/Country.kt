package com.example.moviestreaming.data.search

import java.io.Serializable

data class Country(
    val id: String,
    val name: String,
    val slug: String
): Serializable