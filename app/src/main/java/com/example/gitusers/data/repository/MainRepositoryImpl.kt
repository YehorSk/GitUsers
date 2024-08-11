package com.example.gitusers.data.repository

import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import com.example.gitusers.network.GitApiService
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val gitApiService: GitApiService
) : MainRepository{

    override suspend fun getUsers(): Flow<List<User>>
        = gitApiService.getUsers()

    override suspend fun getUserRepos(name: String): Flow<List<Repository>>
        = gitApiService.getUserRepos(name)

}