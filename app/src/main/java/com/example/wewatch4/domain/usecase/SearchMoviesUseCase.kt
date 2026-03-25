package com.example.wewatch4.domain.usecase

import com.example.wewatch4.domain.model.Movie
import com.example.wewatch4.domain.repository.MovieRepository

class SearchMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(query: String): Result<List<Movie>> {
        return try {
            val results = repository.searchMovies(query)
            Result.success(results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}