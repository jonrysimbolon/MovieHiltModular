package com.jonrysimbolon.feature_trailers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.core.diffcallback.CustomDiffCallback
import com.jonrysimbolon.feature_trailers.databinding.ItemTrailerBinding
import com.jonrysimbolon.core.model.VideoModel
import com.jonrysimbolon.core.utils.startSeconds
import com.jonrysimbolon.core.utils.withDateLongFormat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    private val oldList = ArrayList<VideoModel>()

    fun updateData(newList: List<VideoModel>) {
        val diffCallback = CustomDiffCallback(
            oldList,
            newList,
            { oldItem, newItem ->
                oldItem.id == newItem.id
            }
        ) { oldItem, newItem ->
            oldItem == newItem
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.oldList.clear()
        this.oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel) {
            binding.apply {
                videoNameTv.text = item.name
                publishAtTv.text = item.publishedAt.withDateLongFormat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount() = oldList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = oldList[position]
        holder.bind(item)

        holder.binding.videoYp.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.apply {
                    loadVideo(item.key, startSeconds)
                    pause()
                }
            }
        })
    }
}