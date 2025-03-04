package com.example.moviestreaming.repository

import com.example.moviestreaming.data.FilmDto
import com.example.moviestreaming.data.detail.MovieDetail
import com.example.moviestreaming.data.search.SearchResult
import retrofit2.Call

interface MovieRepository {
    suspend fun getNewMovies(page: Int): FilmDto
    suspend fun getMovie(slug: String): MovieDetail
    suspend fun searchMovies(keyword: String):SearchResult
}