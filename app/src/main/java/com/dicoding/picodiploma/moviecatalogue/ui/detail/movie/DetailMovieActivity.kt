package com.dicoding.picodiploma.moviecatalogue.ui.detail.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.data.Model
import com.dicoding.picodiploma.moviecatalogue.databinding.ActivityDetailBinding
import com.dicoding.picodiploma.moviecatalogue.databinding.ContentDetailBinding
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var detailContentBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                activityDetailBinding.progressBar.visibility = View.VISIBLE

                viewModel.setSelectedMovie(movieId)
                viewModel.getMovie(movieId).observe(this, { course ->
                    populateMovie(course)
                    activityDetailBinding.progressBar.visibility = View.GONE
                })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateMovie(movieEntity: Model) {
        val BASE_IMG = "https://image.tmdb.org/t/p/w500"
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textDescription.text = movieEntity.description
        detailContentBinding.textStudio.text =
            "${resources.getString(R.string.vote)} ${movieEntity.studio.toString()}"
        detailContentBinding.textGenre.text =
            "${resources.getString(R.string.release)} ${movieEntity.genre}"

        Glide.with(this)
            .load(BASE_IMG + movieEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.poster)
    }
}