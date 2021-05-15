package com.dicoding.picodiploma.moviecatalogue.ui.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.Model
import com.dicoding.picodiploma.moviecatalogue.databinding.ItemMovieBinding
import com.dicoding.picodiploma.moviecatalogue.ui.detail.movie.DetailMovieActivity
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<Model>()

    fun setMovies(movies: List<Model>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val BASE_IMG = "https://image.tmdb.org/t/p/w500"

        @SuppressLint("SetTextI18n")
        fun bind(movie: Model) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvStudio.text = "Vote Average: " + movie.studio.toString()
                tvGenre.text = "Release Date: " + movie.genre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.title)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(BASE_IMG + movie.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(poster)
            }
        }
    }
}

