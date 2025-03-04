package com.example.moviestreaming.data.search

import java.io.Serializable

data class SeoOnPage(
    val descriptionHead: String,
    val og_image: List<String>,
    val og_type: String,
    val og_url: String,
    val titleHead: String
): Serializable