package com.cine.en.casa.cineencasa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cine.en.casa.cineencasa.data.model.MovieModel

@Composable
fun MoviesPopular(movieList: List<MovieModel>) {
    CardMovies(movieList = movieList)
}


@Composable
fun MoviesTopRated(){

}

@Composable
fun MoviesTrending(){

}

@Composable
fun CardMovies(movieList: List<MovieModel>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(movieList) { movies ->
                        MovieCard(movie = movies)
                    }
                }
            }

        }
    }

@Composable
fun MovieCard(movie: MovieModel) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(200.dp), // Establece una altura fija para la tarjeta
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
          /*  Image(
                painter = rememberAsyncImagePainter(movie.backdrop_path),
                contentDescription = "Imagen del producto",
                modifier = Modifier.height(1.dp), // Llena el tamaño del contenedor
                contentScale = ContentScale.Crop // Escala la imagen para llenar el contenedor sin deformarla
            )*/
        }

        Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = movie.original_title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .width(130.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                    )
            }
        }
    }