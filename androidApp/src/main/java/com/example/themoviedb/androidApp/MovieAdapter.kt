package com.example.themoviedb.androidApp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themoviedb.shared.entity.Movie

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MoveViewHolder>() {

    var movies: List<Movie>? = null
        set(value) {
            field = value
            if (value.isNullOrEmpty().not()) {
                notifyDataSetChanged()
            }
        }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        return MoveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        movies?.let {
            val movie = it[position]
            Glide.with(holder.imgPoster.context).load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .centerCrop()
                .into(holder.imgPoster)
            holder.txtTitle.text = movie.title
            holder.txtVoteAverage.text = String.format("%.1f/10", movie.voteAverage)
        }
    }

    class MoveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.imgPoster)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtVoteAverage: TextView = itemView.findViewById(R.id.txtVoteAverage)
    }

}