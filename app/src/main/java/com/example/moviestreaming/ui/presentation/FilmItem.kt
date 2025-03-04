package com.example.moviestreaming.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviestreaming.data.Item
import com.example.moviestreaming.utils.Constant


@Composable
fun FilmItem(
    item: Item,
    onItemClick: (Item) -> Unit,
    isSearch: Boolean = false
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .clickable {
                onItemClick(item)
            }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(android.graphics.Color.parseColor("#2f2f39"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 180.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = if (isSearch) Constant.BASE_URL_IMAGE + item.poster_url else item.poster_url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.name,
            modifier = Modifier.padding(4.dp),
            style = TextStyle(
                color = Color.White,
                fontSize = 11.sp
            ),
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}
