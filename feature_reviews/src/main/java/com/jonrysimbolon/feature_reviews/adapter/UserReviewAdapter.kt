package com.jonrysimbolon.feature_reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jonrysimbolon.feature_reviews.Const
import com.jonrysimbolon.feature_reviews.databinding.ItemUserReviewBinding
import com.jonrysimbolon.core.model.UserReviewModel
import com.jonrysimbolon.core.utils.withDateLongFormat

class UserReviewAdapter:PagingDataAdapter<UserReviewModel, UserReviewAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemUserReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserReviewModel) {
            binding.apply {
                item.apply {
                    authorTv.text = author
                    with(this@ViewHolder.itemView.context){
                        createAtTv.text = getString(Const.createAt).plus(createdAt.withDateLongFormat())
                        updateAtTv.text = getString(Const.updateAt).plus(updatedAt.withDateLongFormat())
                    }
                    contentTv.text = content
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserReviewModel>() {
            override fun areItemsTheSame(
                oldItem: UserReviewModel,
                newItem: UserReviewModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UserReviewModel,
                newItem: UserReviewModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}