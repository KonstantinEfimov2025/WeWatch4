package com.example.wewatch4.data.repository

import com.example.wewatch4.data.local.MovieDao
import com.example.wewatch4.data.mapper.toDomain
import com.example.wewatch4.data.mapper.toEntity
import com.example.wewatch4.data.remote.MovieApi
import com.example.wewatch4.domain.model.Movie
import com.example.wewatch4.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val dao: MovieDao,
    private val api: MovieApi
) : MovieRepository {

    override fun getWatchlist(): Flow<List<Movie>> {
        return dao.getAllMovies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        val response = api.searchMovies(query)
        return response.searchResults?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun addMovie(movie: Movie) {
        dao.insertMovie(movie.toEntity())
    }

    override suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie.toEntity())
    }

    override suspend fun deleteCheckedMovies() {
        dao.deleteSelectedMovies()
    }
}