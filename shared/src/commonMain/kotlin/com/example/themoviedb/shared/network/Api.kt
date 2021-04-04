package com.example.themoviedb.shared.network

import com.example.themoviedb.shared.entity.MovieList
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class Api {

    companion object {
        // Get your Api_Key here https://developers.themoviedb.org/3/getting-started/introduction
        const val API_KEY = "YOUR_API_KEY"
        const val MOVIES_URL =
            "https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY&language=en-US&page=1"
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

