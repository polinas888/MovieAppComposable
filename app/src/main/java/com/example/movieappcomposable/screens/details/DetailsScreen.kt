package com.example.movieappcomposable.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieappcomposable.model.Movie
import com.example.movieappcomposable.model.getMovies
import com.example.movieappcomposable.widgets.MovieItem

@Composable
fun DetailsScreen(controller: NavController, movieId: String?) {
    val currentMovieList = getMovies().filter { movie -> movieId == movie.id }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray, elevation = 5.dp
            ) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrowBack",
                        modifier = Modifier.clickable {
                            controller.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(150.dp))
                    Text(text = "Movie")
                }
            }
        }) {
        Surface(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieItem(currentMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalScrollImageView(currentMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollImageView(currentMovieList: List<Movie>) {
    LazyRow {
        items(items = currentMovieList[0].images) { movie ->
            Card(
                modifier = Modifier.padding(10.dp),
                elevation = 5.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(movie).crossfade(true).build(),
                    contentDescription = null,
                    modifier = Modifier.size(240.dp)
                )
            }
        }
    }
}