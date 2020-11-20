package com.everis.coroutines.presentation.activities.main.randomusers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.everis.coroutines.databinding.ViewHolderUserBinding
import com.everis.coroutines.domain.User
import com.everis.coroutines.presentation.activities.main.randomusers.RandomUsersAdapter.UserVH

class RandomUsersAdapter : ListAdapter<User, UserVH>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH =
        UserVH(
            binding = ViewHolderUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserVH(
        private val binding: ViewHolderUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.nameTv.text = user.name
            binding.addressTv.text = user.address
            binding.imageIv.load(user.image) {
                crossfade(true)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}