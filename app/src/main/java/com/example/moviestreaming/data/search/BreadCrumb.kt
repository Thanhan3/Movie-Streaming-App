package com.example.moviestreaming.data.search

import java.io.Serializable

data class BreadCrumb(
    val isCurrent: Boolean,
    val name: String,
    val position: Int
): Serializable