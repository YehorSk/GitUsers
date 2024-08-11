package com.example.gitusers.data.repository

import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getUsers() : Flow<List<User>>

    suspend fun getUserRepos(name: String) : Flow<List<Repository>>

}