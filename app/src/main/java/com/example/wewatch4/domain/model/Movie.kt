package com.example.wewatch4.domain.model

data class Movie(
    val id: Int = 0,
    val title: String,
    val year: String,
    val poster: String,
    val isChecked: Boolean = false
)