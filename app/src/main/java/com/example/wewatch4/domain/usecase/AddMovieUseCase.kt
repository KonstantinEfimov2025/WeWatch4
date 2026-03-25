package com.example.wewatch4.domain.usecase

import com.example.wewatch4.domain.model.Movie
import com.example.wewatch4.domain.repository.MovieRepository

class AddMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) = repository.addMovie(movie)
}