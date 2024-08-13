package com.example.gitusers.data.repository

import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getUsers(since: Int, perPage: Int) : List<User>

    suspend fun getUserRepos(name: String) : List<Repository>

}