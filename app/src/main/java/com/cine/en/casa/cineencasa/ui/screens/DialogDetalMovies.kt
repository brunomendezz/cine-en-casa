package com.cine.en.casa.cineencasa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cine.en.casa.cineencasa.data.model.MovieModel

@Composable
fun ShowDialog(movie: MovieModel, onClick: () -> Unit) {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Mostrar la imagen del backdrop
           item{ Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/original/${movie.backdrop_path}"),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )}

           item {  Spacer(modifier = Modifier.height(16.dp))}

            // Mostrar el título y otros datos de la película
          item{  Text(
                text = movie.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )}

            item {Spacer(modifier = Modifier.height(16.dp))}

           item{ Text(
                text = movie.overview ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )}

           item{ Spacer(modifier = Modifier.height(16.dp))}

            // Botón de cierre
           item {
               Button(
                   onClick = {
                       onClick()
                   },
                   colors = ButtonDefaults.buttonColors(
                       contentColor = Color.White,
                       containerColor = Color.Red
                   )
               ) {
                   Text("Cerrar")
               }
           }
        }
    }
}