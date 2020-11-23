package com.everis.coroutines.presentation.activities.main.favoriteusers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.everis.coroutines.R
import com.everis.coroutines.databinding.ViewHolderUserBinding
import com.everis.coroutines.domain.User

class FavoriteUsersAdapter(
    private val onUserClick: (user: User) -> Unit,
) : ListAdapter<User, FavoriteUsersAdapter.UserVH>(DIFF_CALLBACK) {
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
            binding.favoriteIv.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.imageIv.context,
                    R.drawable.ic_favorite_on
                )
            )
            binding.root.setOnClickListener {
                onUserClick(user)
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