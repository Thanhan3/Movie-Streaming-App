package com.example.moviestreaming.ui.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition.Companion.Center
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviestreaming.R
import com.example.moviestreaming.data.Item
import com.example.moviestreaming.ui.presentation.BottomNavigationBar
import com.example.moviestreaming.ui.presentation.FilmItem
import com.example.moviestreaming.ui.presentation.SearchBar

@Composable
fun MainScreen(
    onItemClick: (Item) -> Unit,
    movies: List<Item>,
    onSearch:(String) -> Unit,
    isSearch : Boolean = false
) {
    androidx.compose.material.Scaffold(
        bottomBar = { BottomNavigationBar() },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorResource(R.color.pink),
                                colorResource(R.color.green)
                            )
                        ),
                        shape = CircleShape
                    )
                    .padding(3.dp)
            ) {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = colorResource(R.color.black3),
                    modifier = Modifier.size(58.dp),
                    contentColor = Color.White,
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.float_icon),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    },
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = Center,
        backgroundColor = colorResource(R.color.blackBackground),
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(color = colorResource(R.color.blackBackground))
        ) {
            Image(
                painter = painterResource(R.drawable.bg1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            MainContent(onItemClick, movies ,onSearch,isSearch)
        }
    }
}

@Composable
fun MainContent(
    onItemClick: (Item) -> Unit,
    movies: List<Item> = emptyList(),
    onSearch: (String) -> Unit,
    isSearch: Boolean = false
) {
    var showNewMoviesLoad by remember { mutableStateOf(true) }
    var showNoMoviesFound by remember { mutableStateOf(false) }

    // Cập nhật loading cho danh sách phim mới
    LaunchedEffect(movies) {
        showNewMoviesLoad = movies.isEmpty()
    }

    // Khi đang search, nếu sau 5 giây danh sách vẫn rỗng thì hiển thị thông báo
    LaunchedEffect(isSearch, movies) {
        if (isSearch && movies.isEmpty()) {
            kotlinx.coroutines.delay(5000L)
            // Nếu sau 5 giây vẫn chưa có phim nào được load thì hiển thị thông báo
            if (movies.isEmpty()) {
                showNoMoviesFound = true
            }
        } else {
            showNoMoviesFound = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Text(
            text = "What would you like to watch?",
            style = TextStyle(
                color = Color.White,
                fontSize = 25.sp
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        SearchBar(
            hint = "Search Movies...",
            onSearch = onSearch
        )

        if (isSearch) {
            SectionTitle(title = "Search Results")
        } else {
            SectionTitle(title = "New Movies")
        }

        // Nếu danh sách phim rỗng
        if (movies.isEmpty()) {
            if (isSearch) {
                if (showNoMoviesFound) {
                    // Sau 5 giây mà vẫn không có phim: hiển thị thông báo
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No movies found",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        )
                    }
                } else {
                    // Trong quá trình chờ kết quả tìm kiếm: hiển thị CircularProgressIndicator
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            } else {
                // Nếu không ở chế độ search (New Movies)
                if (showNewMoviesLoad) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        } else {
            // Nếu danh sách phim không rỗng, hiển thị grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(movies.size) { index ->
                    FilmItem(
                        item = movies[index],
                        onItemClick = onItemClick,
                        isSearch = isSearch
                    )
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(
            color = Color(0xffffc107),
            fontSize = 18.sp
        ),
        modifier = Modifier
            .padding(start = 16.dp, top = 32.dp, bottom = 8.dp),
        fontWeight = FontWeight.Bold
    )
}