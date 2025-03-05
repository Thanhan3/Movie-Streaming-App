package com.example.moviestreaming.data.apiv3

data class Pagination(
    val currentPage: Int,
    val totalItems: Int,
    val totalItemsPerPage: Int,
    val totalPages: Int,
    val updateToday: Int
)