package com.example.moviestreaming

import android.graphics.Movie
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.data.FilmDto
import com.example.moviestreaming.data.Item
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
    private val _movies = MutableStateFlow<List<Item>>(emptyList())// Dữ liệu dạng StateFlow
    val movies: StateFlow<List<Item>> = _movies

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
}