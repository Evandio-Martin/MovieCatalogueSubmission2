package com.dicoding.picodiploma.tvShowcatalogue.ui.detail.tvshow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.TvShow
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

        val factory = ViewModelFactory.getInstance(this)
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
                viewModel.getTvShow().observe(this, { modules ->
                    activityDetailBinding.progressBar.visibility = View.GONE

                })
                viewModel.getTvShow().observe(this, { course -> populateTvShow(course) })
            }
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        detailContentBinding.textTitle.text = tvShowEntity.title
        detailContentBinding.textDescription.text = tvShowEntity.description
        detailContentBinding.textStudio.text = tvShowEntity.studio.toString()
        detailContentBinding.textGenre.text = tvShowEntity.genre

        Glide.with(this)
            .load(tvShowEntity.poster)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.poster)
    }
}