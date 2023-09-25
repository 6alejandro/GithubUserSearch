package com.example.myfirstsubmission.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstsubmission.R
import com.example.myfirstsubmission.databinding.FragmentFollowsBinding

class FollowsFragment : Fragment() {

    private lateinit var binding: FragmentFollowsBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var username: String
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)!!
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollows.layoutManager = layoutManager

        if (isAdded && !isDetached) {
            val followViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[FollowViewModel::class.java]

            followViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }

            followViewModel.followList.observe(viewLifecycleOwner) { followList ->
                if (followList != null) {
                    adapter = UsersAdapter(followList)
                    binding.rvFollows.adapter = adapter
                }
            }

            if (position == 1) {
                followViewModel.getFollowList(username, "followers")
            } else {
                followViewModel.getFollowList(username, "followings")
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.rvFollows.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        var ARG_USERNAME: String = ""
        var ARG_POSITION = "section_number"
    }
}