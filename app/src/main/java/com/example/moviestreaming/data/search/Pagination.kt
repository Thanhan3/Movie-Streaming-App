package com.example.moviestreaming.data.search

import java.io.Serializable

data class Pagination(
    val currentPage: Int,
    val totalItems: Int,
    val totalItemsPerPage: Int,
    val totalPages: Int
): Serializable