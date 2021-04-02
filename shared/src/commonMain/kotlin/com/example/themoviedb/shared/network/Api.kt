package com.example.themoviedb.shared.network

import com.example.themoviedb.shared.entity.MovieList
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class Api {

    companion object {
        const val MOVIES_URL =
            "https://api.themoviedb.org/3/movie/upcoming?api_key=4a150210769c9f31d61a1a8ead06ca56&language=en-US&page=1"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getMovies(): MovieList {
        return httpClient.get(MOVIES_URL)
    }

}

