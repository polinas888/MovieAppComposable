package com.example.movieappcomposable.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcomposable.model.Movie
import com.example.movieappcomposable.model.getMovies
import com.example.movieappcomposable.navigation.MovieScreens
import com.example.movieappcomposable.widgets.MovieItem

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray, elevation = 5.dp
            ) {
                Text(text = "Movie")
            }
        }) {
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(movieList: List<Movie> = getMovies(), navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            LazyColumn {
                items(items = movieList) {
                    MovieItem(movie = it) { movie ->
                        navController.navigate(MovieScreens.DetailsScreen.name + "/$movie")
                    }
                }
            }
        }
    }
}