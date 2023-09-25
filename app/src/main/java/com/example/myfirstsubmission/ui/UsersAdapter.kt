package com.example.myfirstsubmission.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstsubmission.data.response.ItemsItem
import com.example.myfirstsubmission.databinding.ActivityMainBinding
import com.example.myfirstsubmission.databinding.ItemUserBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class UsersAdapter(private val userList: List<ItemsItem>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(githubUser: ItemsItem) {
                binding.tvUsername.text = githubUser.login

                Glide
                    .with(itemView.context)
                    .load(githubUser.avatarUrl)
                    .fitCenter()
                    .into(binding.imgProfile)

                binding.itemLayout.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra("username", githubUser.login)
                    itemView.context.startActivity(intent)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}