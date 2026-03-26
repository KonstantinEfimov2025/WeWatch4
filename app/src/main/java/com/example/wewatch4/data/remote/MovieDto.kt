package com.example.wewatch4.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val poster: String
)

data class MovieResponseDto(
    @SerializedName("Search") val searchResults: List<MovieDto>?,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String?
)