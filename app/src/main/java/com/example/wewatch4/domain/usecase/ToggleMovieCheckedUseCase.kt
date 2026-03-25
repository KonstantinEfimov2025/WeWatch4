package com.example.wewatch4.domain.usecase

import com.example.wewatch4.domain.model.Movie
import com.example.wewatch4.domain.repository.MovieRepository

class ToggleMovieCheckedUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie, isChecked: Boolean) {
        repository.updateMovie(movie.copy(isChecked = isChecked))
    }
}