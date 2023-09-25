package com.example.myfirstsubmission.data.response

import com.google.gson.annotations.SerializedName

data class UsersResponse(

	@field:SerializedName("items")
	val items: List<ItemsItem>
)

data class ItemsItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("name")
	val name: String,
)
