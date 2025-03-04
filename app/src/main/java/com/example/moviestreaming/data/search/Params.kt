package com.example.moviestreaming.data.search

import java.io.Serializable

data class Params(
    val filterCategory: List<Any>,
    val filterCountry: List<Any>,
    val filterType: Any,
    val filterYear: Int,
    val keyword: String,
    val pagination: Pagination,
    val sortField: String,
    val sortType: String,
    val type_slug: String
): Serializable