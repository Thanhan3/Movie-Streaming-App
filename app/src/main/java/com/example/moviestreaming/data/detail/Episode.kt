package com.example.moviestreaming.data.detail

import java.io.Serializable

data class Episode(
    val server_data: List<ServerData>,
    val server_name: String
): Serializable