package com.example.moviestreaming.repository

import com.example.moviestreaming.MovieService
import com.example.moviestreaming.data.FilmDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val api: MovieService
): MovieRepository  {
    override suspend fun getNewMovies(page: Int): FilmDto {
        return withContext(Dispatchers.IO){
             api.getNewMovies(page)
        }
    }
}