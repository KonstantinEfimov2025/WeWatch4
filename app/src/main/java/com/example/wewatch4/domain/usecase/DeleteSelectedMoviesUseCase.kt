package com.example.wewatch4.domain.usecase

import com.example.wewatch4.domain.repository.MovieRepository

class DeleteSelectedMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.deleteCheckedMovies()
}