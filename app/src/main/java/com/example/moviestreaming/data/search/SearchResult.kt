package com.example.moviestreaming.data.search

import java.io.Serializable

data class SearchResult(
    val `data`: Data,
    val msg: String,
    val status: String
): Serializable