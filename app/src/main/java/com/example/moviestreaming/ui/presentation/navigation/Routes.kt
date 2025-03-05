package com.example.moviestreaming.ui.presentation.navigation

import androidx.media3.exoplayer.ExoPlayer
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object MainScreen : Routes()

    @Serializable
    data class DetailScreen(val slug: String) : Routes()

    @Serializable
    data class PlayerScreen(val url: String) : Routes()
}
