package com.example.moviestreaming.repository

import com.example.moviestreaming.data.FilmDto

interface MovieRepository {
    suspend fun getNewMovies(page: Int): FilmDto
}