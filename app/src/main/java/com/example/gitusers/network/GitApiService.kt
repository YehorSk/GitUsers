package com.example.gitusers.network

import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface GitApiService {

    @GET("users")
    suspend fun getUsers() : List<User>

    @GET("users/{name}/repos")
    suspend fun getUserRepos(@Path("name") name: String) : List<Repository>

}