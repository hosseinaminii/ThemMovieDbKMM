package com.example.themoviedb.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Long,
    val title: String,
    @SerialName("release_date")
    val releaseData: String,
    @SerialName("vote_average")
    val voteAverage: Float,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String
)