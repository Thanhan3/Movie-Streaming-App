package com.example.moviestreaming.di

import com.example.moviestreaming.MovieService
import com.example.moviestreaming.repository.MovieRepository
import com.example.moviestreaming.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://phimapi.com/"
    @Provides
    @Singleton
    fun provideMovieApi() : MovieService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieService): MovieRepository {
        return MovieRepositoryImpl(api)
    }


}