package com.cine.en.casa.cineencasa.ui.viewmodel

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cine.en.casa.cineencasa.data.model.MovieModel
import com.cine.en.casa.cineencasa.domain.MoviesServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ListState {
    SUCCESS,
    LOADING,
    ERROR,
}
@HiltViewModel
class MainViewModel @Inject constructor(private val moviesServices: MoviesServices): ViewModel() {

    private val _uiState = MutableLiveData(ListState.LOADING)
    val uiState : LiveData<ListState> = _uiState

    private val _movies = MutableLiveData<List<MovieModel>>(emptyList())
    val movies: LiveData<List<MovieModel>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val moviesList = moviesServices.getAllMovies()
                _movies.value=moviesList
                _uiState.postValue(ListState.SUCCESS)  // Establecer estado exitoso si la obtención de películas fue exitosa
            } catch (e: Exception) {
                _uiState.value = ListState.ERROR // Establecer estado de error si ocurrió una excepción
            }
        }
    }
}