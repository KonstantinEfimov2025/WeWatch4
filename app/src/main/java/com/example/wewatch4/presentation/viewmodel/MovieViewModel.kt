package com.example.wewatch4.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wewatch4.domain.usecase.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val addMovieUseCase: AddMovieUseCase,
    private val toggleMovieCheckedUseCase: ToggleMovieCheckedUseCase,
    private val deleteSelectedMoviesUseCase: DeleteSelectedMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieState())
    val state: StateFlow<MovieState> = _state.asStateFlow()

    init {
        // Автоматически подписываемся на изменения в БД
        viewModelScope.launch {
            getWatchlistUseCase().collect { list ->
                _state.update { it.copy(watchlist = list) }
            }
        }
    }

    fun handleIntent(intent: MovieIntent) {
        when (intent) {
            is MovieIntent.Search -> performSearch(intent.query)
            is MovieIntent.Add -> viewModelScope.launch { addMovieUseCase(intent.movie) }
            is MovieIntent.ToggleChecked -> viewModelScope.launch {
                toggleMovieCheckedUseCase(intent.movie, intent.isChecked)
            }
            MovieIntent.DeleteSelected -> viewModelScope.launch { deleteSelectedMoviesUseCase() }
        }
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            searchMoviesUseCase(query)
                .onSuccess { results ->
                    _state.update { it.copy(searchResults = results, isLoading = false) }
                }
                .onFailure { error ->
                    _state.update { it.copy(error = error.message, isLoading = false) }
                }
        }
    }
}