package com.example.moviestreaming.repository

import com.example.moviestreaming.MovieService
import com.example.moviestreaming.data.FilmDto
import com.example.moviestreaming.data.detail.MovieDetail
import com.example.moviestreaming.data.search.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MovieRepositoryImpl(
    private val api: MovieService
) : MovieRepository {
    override suspend fun getNewMovies(page: Int): FilmDto {
        return withContext(Dispatchers.IO) {
            api.getNewMovies(page)
        }
    }

    override suspend fun getMovie(slug: String): MovieDetail {
        return withContext(Dispatchers.IO) {
            api.getMovie(slug)
        }
    }

    override suspend fun searchMovies(keyword: String): SearchResult {
        return withContext(Dispatchers.IO) {
            api.searchMovies(keyword)
        }
    }
}