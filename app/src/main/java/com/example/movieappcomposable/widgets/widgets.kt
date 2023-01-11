package com.example.movieappcomposable.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieappcomposable.model.Movie
import com.example.movieappcomposable.model.getMovies

@Preview
@Composable
fun MovieItem(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    val expanded = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.padding(4.dp).fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 8.dp
    ) {
        Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(100.dp), shape = RectangleShape) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = null
                )
            }
            Column(modifier = Modifier.padding(15.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.caption)
                Text(text = "Release: ${movie.year}", style = MaterialTheme.typography.caption)
                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp, fontWeight = FontWeight.Bold)) {
                                append(movie.plot)
                            }
                        })
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }
                Icon(imageVector = if(expanded.value) Icons.Filled.KeyboardArrowUp else  Icons.Filled.KeyboardArrowDown , contentDescription = "Down arrow", modifier =
                Modifier.size(33.dp)
                    .clickable {
                        expanded.value = !expanded.value
                    },
                    tint = Color.DarkGray)
            }
        }
    }
}
