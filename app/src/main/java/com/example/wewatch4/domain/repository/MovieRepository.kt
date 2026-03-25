package com.example.wewatch4.domain.repository

import com.example.wewatch4.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getWatchlist(): Flow<List<Movie>>
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun addMovie(movie: Movie)
    suspend fun updateMovie(movie: Movie)
    suspend fun deleteCheckedMovies()
}