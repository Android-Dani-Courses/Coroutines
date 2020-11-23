package com.everis.coroutines.presentation.activities.main.randomusers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.everis.coroutines.R
import com.everis.coroutines.databinding.ViewHolderUserBinding
import com.everis.coroutines.domain.User

class RandomUsersAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val favoriteUsersLD: LiveData<List<User>>,
    private val onUserClick: (user: User) -> Unit,
) : ListAdapter<User, RandomUsersAdapter.UserVH>(DIFF_CALLBACK) {
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
        private lateinit var favoriteUsersObserver: (t: List<User>) -> Unit
        fun bind(user: User) {
            binding.nameTv.text = user.name
            binding.addressTv.text = user.address
            binding.imageIv.load(user.image) {
                crossfade(true)
            }
            if (::favoriteUsersObserver.isInitialized) {
                favoriteUsersLD.removeObserver(favoriteUsersObserver)
            }
            favoriteUsersObserver = { users ->
                if (users.contains(user)) {
                    binding.favoriteIv.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.imageIv.context,
                            R.drawable.ic_favorite_on
                        )
                    )
                } else {
                    binding.favoriteIv.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.imageIv.context,
                            R.drawable.ic_favorite_off
                        )
                    )
                }
            }
            favoriteUsersLD.observe(lifecycleOwner, favoriteUsersObserver)
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