package com.example.themoviedb.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviedb.shared.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.shared.network.Api
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rclMovie = findViewById<RecyclerView>(R.id.rclMovie)
        val adapter = MovieAdapter()

        rclMovie.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            this.adapter = adapter
        }

        mainScope.launch {
            try {
                val result = Api().getMovies()
                adapter.movies = result.movies
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
