package com.example.moviestreaming.ui.presentation.screen

import android.content.res.Configuration
import android.graphics.Color.BLACK
import android.graphics.Color.GRAY
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.moviestreaming.PlayerViewModel
import com.example.moviestreaming.R

@OptIn(UnstableApi::class)
@Composable
fun PlayVideoComponent(
    url: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val playerViewModel: PlayerViewModel = hiltViewModel()
    val exoPlayer by remember { mutableStateOf(playerViewModel.exoPlayer) }
    LaunchedEffect(url) {
        playerViewModel.initPlayer(context, url)
    }
    var isFullscreen by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration.orientation) {
        isFullscreen = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    if (exoPlayer == null) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                lifecycle = event
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
                exoPlayer!!.release()
            }
        }

        val playerViewModifier = if (isFullscreen) {
            Modifier.fillMaxSize()
        } else {
            modifier
                .fillMaxWidth()
                .height(250.dp)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.blackBackground))
        ) {
            AndroidView(
                modifier = playerViewModifier
                    .background(color = Color(BLACK))
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                // Toggle fullscreen mode
                                isFullscreen = !isFullscreen
                            }
                        )
                    },
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        useController = true
                    }
                },
                update = { playerView ->
                    playerView.player = exoPlayer
                    when (lifecycle) {
                        Lifecycle.Event.ON_PAUSE -> {
                            playerView.onPause()
                            exoPlayer!!.pause()
                        }

                        Lifecycle.Event.ON_RESUME -> {
                            playerView.onResume()
                            exoPlayer!!.play()
                        }

                        else -> Unit
                    }
                }
            )
        }
    }
}