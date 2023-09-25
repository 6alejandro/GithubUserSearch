package com.example.myfirstsubmission.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.myfirstsubmission.R
import com.example.myfirstsubmission.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUserData()

        setViewPager()
    }

    private fun setUserData() {
        username = intent.getStringExtra("username")!!

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        detailViewModel.getUsersDetail(username)

        detailViewModel.userDetail.observe(this) { userDetail ->
            Glide
                .with(this)
                .load(userDetail.avatarUrl)
                .fitCenter()
                .into(binding.ivProfile)

            binding.tvUsername.text = userDetail.login
            binding.tvName.text = userDetail.name

            val followersText = getString(R.string.followers_template, userDetail.followers.toString())
            val followingText = getString(R.string.following_template, userDetail.following.toString())

            binding.tvTotalFollowers.text = followersText
            binding.tvTotalFollowing.text = followingText
        }
    }

    private fun setViewPager() {
        val adapter = SectionsPagerAdapter(this, username)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabText = this.getString(TAB_TITLES[position])
            tab.text = tabText
        }.attach()
    }

    private fun showLoading(isLoading: Boolean) {
        with(binding) {
            tvUsername.visibility = if (isLoading) View.GONE else View.VISIBLE
            tvName.visibility = if (isLoading) View.GONE else View.VISIBLE
            tvTotalFollowers.visibility = if (isLoading) View.GONE else View.VISIBLE
            tvTotalFollowing.visibility = if (isLoading) View.GONE else View.VISIBLE
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    companion object {
        private val TAB_TITLES = listOf(
            R.string.followers_tab,
            R.string.followings_tab
        )
    }
}