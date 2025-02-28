package com.example.moviestreaming

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviestreaming.ui.presentation.PlayVideoComponent
import com.example.moviestreaming.ui.theme.MovieStreamingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieStreamingTheme {
                val context = LocalContext.current
                val url ="https://s3.phim1280.tv/20240524/znnHM21Z/index.m3u8"
                val playerViewModel : PlayerViewModel = hiltViewModel()
                LaunchedEffect(key1 = Unit){
                    playerViewModel.initPlayer(context, url)
                }
                playerViewModel.exoPlayer?.let {
                    PlayVideoComponent(exoPlayer = it)
                }
            }
        }
    }
}


