package com.example.moviestreaming.ui.presentation.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.moviestreaming.MovieViewModel
import com.example.moviestreaming.PlayerViewModel
import com.example.moviestreaming.R
import com.example.moviestreaming.ui.presentation.screen.DetailScreen
import com.example.moviestreaming.ui.presentation.screen.MainScreen
import com.example.moviestreaming.ui.presentation.screen.PlayVideoComponent


@Composable
fun NavGraphSetup(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen
    ) {
        composable<Routes.MainScreen> {
            val mainViewModel = hiltViewModel<MovieViewModel>()
            LaunchedEffect(Unit) {
                mainViewModel.fetchMovies()
            }
            val movies by mainViewModel.movies.collectAsState()
            val searchMovies by mainViewModel.searchMovies.collectAsState()
            val isSearch by mainViewModel.isSearch.collectAsState()
            MainScreen(
                onItemClick = {
                    navController.navigate(Routes.DetailScreen(slug = it.slug))
                },
                movies = if (isSearch) searchMovies?.data?.items ?: emptyList() else movies,
                onSearch = { query ->
                    mainViewModel.searchMovies(query)
                    mainViewModel.setQuery(query)
                },
                isSearch = isSearch
            )
        }
        composable<Routes.DetailScreen> {
            val mainViewModel = hiltViewModel<MovieViewModel>()
            val slug = it.toRoute<Routes.DetailScreen>().slug
            LaunchedEffect(slug) {
                mainViewModel.getMovie(slug)
            }
            val movie by mainViewModel.movie.collectAsState()
            val localContext = LocalContext.current
            fun onPlayClick(url: String) {
                navController.navigate(Routes.PlayerScreen(url))
            }
            if (movie == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(R.color.blackBackground)),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            } else {
                movie?.let {
                    DetailScreen(
                        filmItem = it,
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onPlayClick = {
                            onPlayClick(it)
                        }
                    )
                }
            }
        }
        composable<Routes.PlayerScreen> {
            val url = it.toRoute<Routes.PlayerScreen>().url
            PlayVideoComponent(url)
        }

    }
}