package com.example.wewatch4.data.mapper

import com.example.wewatch4.data.local.MovieEntity
import com.example.wewatch4.data.remote.MovieDto
import com.example.wewatch4.domain.model.Movie

// Из БД в Domain
fun MovieEntity.toDomain(): Movie {
    return Movie(id, title, year, poster, isChecked)
}

// Из Domain в БД
fun Movie.toEntity(): MovieEntity {
    return MovieEntity(id, title, year, poster, isChecked)
}

// Из Сети в Domain
fun MovieDto.toDomain(): Movie {
    return Movie(
        title = this.title,
        year = this.year,
        poster = this.poster,
        isChecked = false
    )
}