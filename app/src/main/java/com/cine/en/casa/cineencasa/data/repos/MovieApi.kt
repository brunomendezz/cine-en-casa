package com.cine.en.casa.cineencasa.data.repos

import com.cine.en.casa.cineencasa.data.model.ResultMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular?api_key=8d86bd8bbcc1c831d29fdc47be9b97ad&language=es-ES")
    suspend fun getAllMovies(@Query("page")page:Int): Response<ResultMovieModel>

    @GET("movie/top_rated?api_key=8d86bd8bbcc1c831d29fdc47be9b97ad&language=es-ES")
    suspend fun getAllMoviesTopRated(@Query("page")page: Int): Response<ResultMovieModel>

    @GET("trending/all/week?api_key=8d86bd8bbcc1c831d29fdc47be9b97ad&language=es-ES")
    suspend fun getMoviesTrending(@Query("page")page: Int): Response<ResultMovieModel>
}