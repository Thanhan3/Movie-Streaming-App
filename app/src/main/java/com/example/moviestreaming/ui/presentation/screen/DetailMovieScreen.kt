package com.example.moviestreaming.ui.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviestreaming.R
import com.example.moviestreaming.data.detail.MovieDetail

@Composable
fun DetailScreen(filmItem: MovieDetail, onBackClick: () -> Unit) {
    val scrollState = rememberScrollState()
    val isLoading = remember { mutableStateOf(false) }
    val episode = filmItem.episodes[1].server_data
    Log.d("episode", "DetailScreen: $episode")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blackBackground))
    ) {
        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier.height(400.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 16.dp, top = 48.dp)
                            .clickable { onBackClick() }
                    )
                    Image(
                        painter = painterResource(R.drawable.fav),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 16.dp, top = 48.dp)
                            .align(Alignment.TopEnd)
                    )
                    AsyncImage(
                        model = filmItem.movie.poster_url,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.1f
                    )
                    AsyncImage(
                        model = filmItem.movie.poster_url,
                        contentDescription = "",
                        modifier = Modifier
                            .size(210.dp, 300.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .align(Alignment.BottomCenter),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(R.color.black2),
                                        colorResource(R.color.black1)
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, Float.POSITIVE_INFINITY)
                                )
                            )
                    )

                    Text(
                        text = filmItem.movie.name,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 27.sp
                        ),
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.cal),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "Release: ${filmItem.movie.year}",
                            style = TextStyle(
                                color = Color.White
                            )
                        )

                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "Tmdb: ${filmItem.movie.tmdb.vote_average}",
                            style = TextStyle(
                                color = Color.White
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Summary",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = filmItem.movie.content,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Actors",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        items(filmItem.movie.actor.size) {
                            Text(
                                text = "${filmItem.movie.actor[it]}, ",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Episode",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(episode.size) { it ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                elevation = 4.dp,
                                backgroundColor = Color(0xFF1F1F1F) // Màu nền cho Card
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp), // Padding bên trong Card
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        text = episode[it].name,
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}