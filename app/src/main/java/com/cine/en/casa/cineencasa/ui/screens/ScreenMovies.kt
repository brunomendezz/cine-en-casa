package com.cine.en.casa.cineencasa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cine.en.casa.cineencasa.data.model.MovieModel

@Composable
fun FeedMovies(
    moviesPopularList: List<MovieModel>,
    moviesTrendingList: List<MovieModel>,
    moviesTopRatedList: List<MovieModel>,
    onClick: (movie: MovieModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item {
            Text(
                text = "Las más populares",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 3.dp, start = 9.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 9.dp)
            ) {
                items(moviesPopularList) { movie ->
                    MovieCard(movie = movie){
                        onClick(it)
                    }
                }
            }
        }

        item {
            Text(
                text = "Tendencia",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 3.dp, start = 9.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 9.dp)
            ) {
                items(moviesTrendingList) { movie ->
                    MovieCard(movie = movie){
                        onClick(it)
                    }
                }
            }
        }

        item {
            Text(
                text = "Las más votadas",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 3.dp, start = 9.dp)
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 9.dp)
            ) {
                items(moviesTopRatedList) { movie ->
                    MovieCard(movie = movie){
                        onClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun Show(movie: MovieModel) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Película seleccionada")
            },
            text = {
                Text(text = "Has seleccionado la película: ${movie.title}")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text("Cerrar")
                }
            }
        )
    }
}

@Composable
fun MovieCard(movie: MovieModel, onClick: (movie:MovieModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(120.dp)
            .clickable(onClick = { onClick(movie) })
        ,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/${movie.poster_path}"),
                contentDescription = "Imagen del producto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(200.dp) // Llena el tamaño del contenedor
            )
        }
    }
}