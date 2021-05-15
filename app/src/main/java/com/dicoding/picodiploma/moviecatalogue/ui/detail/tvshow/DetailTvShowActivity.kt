package com.dicoding.picodiploma.tvShowcatalogue.ui.detail.tvshow

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
import com.dicoding.picodiploma.moviecatalogue.databinding.ActivityTvShowDetailBinding
import com.dicoding.picodiploma.moviecatalogue.databinding.ContentTvShowDetailBinding
import com.dicoding.picodiploma.moviecatalogue.ui.detail.tvshow.DetailTvShowViewModel
import com.dicoding.picodiploma.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var detailContentBinding: ContentTvShowDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TV_SHOW)
            if (tvShowId != null) {
                activityDetailBinding.progressBar.visibility = View.VISIBLE

                viewModel.setSelectedTvShow(tvShowId)
                viewModel.getTvShow(tvShowId).observe(this, { course ->
                    populateTvShow(course)
                    activityDetailBinding.progressBar.visibility = View.GONE
                })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateTvShow(tvShowEntity: Model) {
        val BASE_IMG = "https://image.tmdb.org/t/p/w500"
        detailContentBinding.textTitle.text = tvShowEntity.title
        detailContentBinding.textDescription.text = tvShowEntity.description
        detailContentBinding.textStudio.text =
            "${resources.getString(R.string.vote)} ${tvShowEntity.studio.toString()}"
        detailContentBinding.textGenre.text =
            "${resources.getString(R.string.release)} ${tvShowEntity.genre}"

        Glide.with(this)
            .load(BASE_IMG + tvShowEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.poster)
    }
}