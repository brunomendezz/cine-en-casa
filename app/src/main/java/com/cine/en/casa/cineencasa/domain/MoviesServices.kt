package com.cine.en.casa.cineencasa.domain

import com.cine.en.casa.cineencasa.data.model.MovieModel
import com.cine.en.casa.cineencasa.data.repos.MovieRestRespository
import javax.inject.Inject

class MoviesServices @Inject constructor(var movieRepo:MovieRestRespository) {

    suspend fun getAllMovies(page: Int):List<MovieModel>{
        return movieRepo.getAllMovies(page)
    }

    suspend fun getTrendingMovies(page: Int):List<MovieModel>{
        return movieRepo.getMoviesTrending(page)
    }

    suspend fun getTopRatedMovies(page: Int):List<MovieModel>{
        return movieRepo.getAllMoviesTopRated(page)
    }

}