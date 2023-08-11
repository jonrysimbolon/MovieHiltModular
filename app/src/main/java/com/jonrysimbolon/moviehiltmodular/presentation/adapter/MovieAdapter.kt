package com.jonrysimbolon.moviehiltmodular.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.utils.setImageUrl
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.ItemMovieBinding
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieAdapter @Inject constructor(
) : PagingDataAdapter<MovieModel, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onClickItem: ((ViewHolder, MovieModel) -> Unit)? = null

    class ViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieModel) {
            binding.apply {
                tvItemName.text = item.title
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)

        setImageUrl(
            item?.posterPath.toString(),
            holder.binding.ivItemPhoto,
            errorImage = R.drawable.notfound,
            placeHolderImage = R.drawable.cinema
        )

        holder.itemView.setOnClickListener {
            onClickItem?.let { onClickItem ->
                getItem(position)?.let { movieModel ->
                    onClickItem(holder, movieModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MovieModel,
                newItem: MovieModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}