package com.example.moviestreaming

import com.example.moviestreaming.data.FilmDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("danh-sach/phim-moi-cap-nhat")
    suspend fun getNewMovies(@Query("page") page: Int): FilmDto
}