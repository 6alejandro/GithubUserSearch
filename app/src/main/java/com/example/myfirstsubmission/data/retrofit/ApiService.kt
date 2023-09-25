package com.example.myfirstsubmission.data.retrofit

import com.example.myfirstsubmission.data.response.ItemsItem
import com.example.myfirstsubmission.data.response.UsersDetailResponse
import com.example.myfirstsubmission.data.response.UsersResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") username: String
    ): Call<UsersResponse>

    @GET("users/{username}")
    fun getUsersDetail(
        @Path("username") username: String
    ): Call<UsersDetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowings(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}