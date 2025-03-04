package com.example.moviestreaming.ui.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object MainScreen : Routes()

    @Serializable
    data class DetailScreen(val slug: String) : Routes()

}
