package com.cine.en.casa.cineencasa.data.model

import com.google.gson.annotations.SerializedName

data class ResultMovieModel(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieModel>,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("total_pages") val total_pages: Int
)