package com.cine.en.casa.cineencasa.data.repos

import com.cine.en.casa.cineencasa.data.model.MovieModel
import retrofit2.Retrofit.Builder
import javax.inject.Inject

class MovieRestRespository @Inject constructor(builder:Builder): MoviesRepository {
    var retrofit = builder.build()
    val movieApi = retrofit.create(MovieApi::class.java)

    override suspend fun getAllMovies(page: Int): List<MovieModel> {
        val call = movieApi.getAllMovies(page)
        if (call.isSuccessful){
            return call.body()?.results ?: emptyList()
        }
        return emptyList()
    }

    override suspend fun getAllMoviesTopRated(page: Int): List<MovieModel> {
        val call = movieApi.getAllMoviesTopRated(page)
        if (call.isSuccessful){
            return call.body()?.results ?: emptyList()
        }
       return emptyList()
    }

    override suspend fun getMoviesTrending(peliculas: Int): List<MovieModel> {
     val call = movieApi.getMoviesTrending(peliculas)
        if (call.isSuccessful){
            return call.body()?.results ?: emptyList()
        }
        return emptyList()
    }

}