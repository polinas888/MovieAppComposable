package com.example.movieappcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieappcomposable.navigation.MovieNavigation
import com.example.movieappcomposable.ui.theme.MovieAppComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { MovieNavigation() }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MovieAppComposableTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp { MovieNavigation() }
}