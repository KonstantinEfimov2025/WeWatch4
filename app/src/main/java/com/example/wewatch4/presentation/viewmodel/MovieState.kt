package com.example.wewatch4.presentation.viewmodel

import com.example.wewatch4.domain.model.Movie

data class MovieState(
    val watchlist: List<Movie> = emptyList(),
    val searchResults: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class MovieIntent {
    data class Search(val query: String) : MovieIntent()
    data class Add(val movie: Movie) : MovieIntent()
    data class ToggleChecked(val movie: Movie, val isChecked: Boolean) : MovieIntent()
    object DeleteSelected : MovieIntent()
}