package com.example.gitusers.network

import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApiService {

    @GET("users")
    suspend fun getUsers(@Query("since") since: Int, @Query("per_page") perPage: Int) : List<User>

    @GET("users/{name}/repos")
    suspend fun getUserRepos(@Path("name") name: String) : List<Repository>

}