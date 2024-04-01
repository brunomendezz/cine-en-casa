package com.cine.en.casa.cineencasa.data.repos

import com.cine.en.casa.cineencasa.data.model.MovieModel
import com.cine.en.casa.cineencasa.data.model.ResultMovieModel
import retrofit2.Response

interface MoviesRepository {
    suspend fun getAllMovies(page:Int): List<MovieModel>

    suspend fun getAllMoviesTopRated(page: Int): List<MovieModel>

    suspend fun getMoviesTrending(peliculas: Int): List<MovieModel>
}