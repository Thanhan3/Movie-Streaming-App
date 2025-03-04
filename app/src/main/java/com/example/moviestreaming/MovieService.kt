package com.example.moviestreaming

import android.telecom.Call
import com.example.moviestreaming.data.FilmDto
import com.example.moviestreaming.data.detail.MovieDetail
import com.example.moviestreaming.data.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("danh-sach/phim-moi-cap-nhat")
    suspend fun getNewMovies(@Query("page") page: Int): FilmDto

    @GET("phim/{slug}")
    suspend fun getMovie(@Path("slug") slug: String): MovieDetail

    @GET("v1/api/tim-kiem")
    suspend fun searchMovies(@Query("keyword") keyword: String):SearchResult

}