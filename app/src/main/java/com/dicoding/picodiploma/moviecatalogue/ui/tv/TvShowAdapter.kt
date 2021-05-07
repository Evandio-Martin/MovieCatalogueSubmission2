package com.dicoding.picodiploma.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue.R
import com.dicoding.picodiploma.moviecatalogue.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.databinding.ItemsTvShowBinding
import com.dicoding.picodiploma.tvShowcatalogue.ui.detail.tvshow.DetailTvShowActivity
import java.util.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.CourseViewHolder>() {
    private var listTvshows = ArrayList<TvShowEntity>()

    fun setTvshows(tvshows: List<TvShowEntity>?) {
        if (tvshows == null) return
        this.listTvshows.clear()
        this.listTvshows.addAll(tvshows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val tvshow = listTvshows[position]
        holder.bind(tvshow)
    }

    override fun getItemCount(): Int = listTvshows.size


    class CourseViewHolder(private val binding: ItemsTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvshow.title
                tvStudio.text = tvshow.studio.toString()
                tvGenre.text = tvshow.genre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvshow.title)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvshow.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(poster)
            }
        }
    }
}

