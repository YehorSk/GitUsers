package com.example.gitusers.data.repository

import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import com.example.gitusers.network.GitApiService
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val gitApiService: GitApiService
) : MainRepository{

    override suspend fun getUsers(since: Int, perPage: Int): List<User>
        = gitApiService.getUsers(since = since,perPage = perPage)

    override suspend fun getUserRepos(name: String): List<Repository>
        = gitApiService.getUserRepos(name)

}