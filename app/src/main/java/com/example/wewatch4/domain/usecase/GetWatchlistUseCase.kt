package com.example.wewatch4.domain.usecase

import com.example.wewatch4.domain.model.Movie
import com.example.wewatch4.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetWatchlistUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = repository.getWatchlist()
}