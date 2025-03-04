package com.example.moviestreaming.ui.presentation

import android.content.res.Configuration
import android.graphics.Color.*
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView


@OptIn(UnstableApi::class)
@Composable
fun PlayVideoComponent(
    exoPlayer: ExoPlayer,
    modifier: Modifier = Modifier,
) {
    var lifecycle by remember { mutableStateOf(Lifecycle.Event.ON_CREATE) }
    var isFullscreen by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current


    val context = LocalContext.current
    val configuration = context.resources.configuration


    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        isFullscreen = true
    } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        isFullscreen = false
    }


    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    val playerViewModifier = if (isFullscreen) {
        Modifier.fillMaxSize()
    } else {
        modifier.fillMaxWidth().height(250.dp)
    }

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
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    playerView.onResume()
                    exoPlayer.play()
                }
                else -> Unit
            }
        }
    )
}
