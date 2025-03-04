package com.example.moviestreaming

import android.graphics.Movie
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.data.FilmDto
import com.example.moviestreaming.data.Item
import com.example.moviestreaming.data.detail.MovieDetail
import com.example.moviestreaming.data.search.SearchResult
import com.example.moviestreaming.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movies = MutableStateFlow<List<Item>>(emptyList())
    val movies: StateFlow<List<Item>> = _movies

    private val _movie = MutableStateFlow<MovieDetail?>(null)
    val movie: StateFlow<MovieDetail?> = _movie

    private val _searchMovies = MutableStateFlow<SearchResult?>(null)
    val searchMovies: StateFlow<SearchResult?> = _searchMovies

    private val query = MutableStateFlow("")
    private val _isSearch = MutableStateFlow(false)
    val isSearch: StateFlow<Boolean> = _isSearch

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getNewMovies(1)
                _movies.value = response.items
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovie(slug: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovie(slug)
                _movie.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setQuery(text: String) {
        query.value = text
        _isSearch.value = text.isNotEmpty()
    }

    fun searchMovies(keyword: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchMovies(keyword) // Gọi API
                _searchMovies.value = response // Cập nhật LiveData/StateFlow
                Log.d("Search", response.toString()) // In kết quả
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}