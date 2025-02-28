package com.example.moviestreaming.data

import java.io.Serializable

data class Pagination(
    val currentPage: Int,
    val totalItems: Int,
    val totalItemsPerPage: Int,
    val totalPages: Int
): Serializable