package com.example.moviestreaming.data.detail

import java.io.Serializable

data class ServerData(
    val filename: String,
    val link_embed: String,
    val link_m3u8: String,
    val name: String,
    val slug: String
): Serializable