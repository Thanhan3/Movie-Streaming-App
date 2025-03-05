package com.example.moviestreaming.data.apiv3

data class Item(
    val _id: String,
    val category: List<Category>,
    val country: List<Country>,
    val episode_current: String,
    val lang: String,
    val modified: Modified,
    val name: String,
    val origin_name: String,
    val poster_url: String,
    val quality: String,
    val slug: String,
    val sub_docquyen: Boolean,
    val thumb_url: String,
    val time: String,
    val type: String,
    val year: Int
)