package com.cine.en.casa.cineencasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cine.en.casa.cineencasa.ui.screens.FeedMovies
import com.cine.en.casa.cineencasa.ui.screens.ShowDialog
import com.cine.en.casa.cineencasa.ui.viewmodel.ListState
import com.cine.en.casa.cineencasa.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
        }
    }
}
    @Composable
    fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    val uiState by mainViewModel.uiState.observeAsState()
    val moviesPopular by mainViewModel.moviesPopular.observeAsState()
    val moviesTrending by mainViewModel.moviesTrending.observeAsState()
    val moviesTopRated by mainViewModel.moviesTopRated.observeAsState()
        val movieSlect by mainViewModel.movieSelect.observeAsState()
        val showDialog by mainViewModel.showDialog.observeAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when (uiState) {
                ListState.ERROR-> {
                    Box {
                        /*LoginUser(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    onClickAction = { userName ->
                                        mainViewModel.createUser(userName)
                                    }*/
                    }
                    LaunchedEffect(snackbarHostState) {
                        // Show snackbar using a coroutine, when the coroutine is cancelled the
                        // snackbar will automatically dismiss. This coroutine will cancel whenever
                        // `state.hasError` is false, and only start when `state.hasError` is true
                        // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
                        snackbarHostState.showSnackbar(
                            message = "ERROR",
                            actionLabel = "Retry message"
                        )
                    }
                }

                ListState.LOADING-> {
                    LaunchedEffect(snackbarHostState) {
                        // Show snackbar using a coroutine, when the coroutine is cancelled the
                        // snackbar will automatically dismiss. This coroutine will cancel whenever
                        // `state.hasError` is false, and only start when `state.hasError` is true
                        // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
                        snackbarHostState.showSnackbar(
                            message = "Loading",
                        )
                    }
                }

                ListState.SUCCESS -> {
                    FeedMovies(
                        moviesPopularList = moviesPopular ?: emptyList(),
                        moviesTrendingList = moviesTrending ?: emptyList(),
                        moviesTopRatedList = moviesTopRated ?: emptyList(),
                        onClick = {
                            mainViewModel.movieSelect(it)
                            mainViewModel.showDialog
                        }
                    )
                    if (showDialog==true)
                    movieSlect?.let { ShowDialog(movie = it){
                        mainViewModel.dissmisDialog()
                    } }
                }

                null -> TODO()
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
  MainScreen()
}