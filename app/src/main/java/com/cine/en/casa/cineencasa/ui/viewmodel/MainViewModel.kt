package com.cine.en.casa.cineencasa.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cine.en.casa.cineencasa.data.model.MovieModel
import com.cine.en.casa.cineencasa.domain.MoviesServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ListState {
    SUCCESS,
    LOADING,
    ERROR,
}
@HiltViewModel
class MainViewModel @Inject constructor(private val moviesServices: MoviesServices): ViewModel() {

private val _showDialog= MutableLiveData(false)
    val showDialog :LiveData<Boolean> = _showDialog

    private val _uiState = MutableLiveData(ListState.LOADING)
    val uiState : LiveData<ListState> = _uiState

    private val _movieSelect = MutableLiveData<MovieModel>()
    val movieSelect : LiveData<MovieModel> = _movieSelect

    private val _moviesPopular = MutableLiveData<List<MovieModel>>(emptyList())
    val moviesPopular: LiveData<List<MovieModel>> = _moviesPopular

    private val _moviesTrending = MutableLiveData<List<MovieModel>>(emptyList())
    val moviesTrending: LiveData<List<MovieModel>> = _moviesTrending

    private val _moviesTopRated = MutableLiveData<List<MovieModel>>(emptyList())
    val moviesTopRated: LiveData<List<MovieModel>> = _moviesTopRated

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val moviesList = moviesServices.getPopularMovies()
                _moviesPopular.value=moviesList
                _moviesTrending.value=moviesServices.getTrendingMovies()
                _moviesTopRated.value=moviesServices.getTopRatedMovies()

                _uiState.postValue(ListState.SUCCESS)  // Establecer estado exitoso si la obtención de películas fue exitosa
            } catch (e: Exception) {
                _uiState.value = ListState.ERROR // Establecer estado de error si ocurrió una excepción
            }
        }
    }

    fun movieSelect(movie: MovieModel) {
        _movieSelect.postValue(movie)
        _showDialog.postValue(true)

    }

    fun dissmisDialog(){
        _showDialog.postValue(false)
    }
}