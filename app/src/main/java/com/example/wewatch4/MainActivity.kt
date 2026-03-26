package com.example.wewatch4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wewatch4.data.local.MovieDatabase
import com.example.wewatch4.data.remote.RetrofitClient
import com.example.wewatch4.data.repository.MovieRepositoryImpl
import com.example.wewatch4.domain.usecase.*
import com.example.wewatch4.presentation.ui.MainScreen
import com.example.wewatch4.presentation.ui.SearchScreen
import com.example.wewatch4.presentation.viewmodel.MovieViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Инициализируем зависимости вручную
        val database = MovieDatabase.getDatabase(this)
        val repository = MovieRepositoryImpl(database.movieDao(), RetrofitClient.api)

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieViewModel(
                    GetWatchlistUseCase(repository),
                    SearchMoviesUseCase(repository),
                    AddMovieUseCase(repository),
                    ToggleMovieCheckedUseCase(repository),
                    DeleteSelectedMoviesUseCase(repository)
                ) as T
            }
        }

        setContent {
            val navController = rememberNavController()
            val viewModel: MovieViewModel = viewModel(factory = factory)

            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(viewModel) { navController.navigate("search") }
                }
                composable("search") {
                    SearchScreen(viewModel) { navController.popBackStack() }
                }
            }
        }
    }
}