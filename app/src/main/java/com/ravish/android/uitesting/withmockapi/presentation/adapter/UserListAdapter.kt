package com.ravish.android.uitesting.withmockapi.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ravish.android.uitesting.withmockapi.data.model.User
import com.ravish.android.uitesting.withmockapi.databinding.UserListItemBinding
import com.ravish.android.uitesting.withmockapi.presentation.BoundHolder
import com.ravish.android.uitesting.withmockapi.presentation.viewBinding

class UserListAdapter : ListAdapter<User, UserListAdapter.ViewHolder>(UserItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(parent: ViewGroup) :
        BoundHolder<UserListItemBinding>(parent.viewBinding(UserListItemBinding::inflate)) {
        fun bind(user: User) {
            with(binding) {
                name.text = user.name
                email.text = user.email
                username.text = user.username
            }
        }
    }

    object UserItemDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem

    }
}