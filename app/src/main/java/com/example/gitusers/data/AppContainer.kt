package com.example.gitusers.data

import com.example.gitusers.data.repository.MainRepository
import com.example.gitusers.data.repository.MainRepositoryImpl
import com.example.gitusers.network.GitApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val repository: MainRepository
}

class DefaultAppContainer : AppContainer{

    private val baseUrl = "https://api.github.com/"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    val retrofitService : GitApiService by lazy {
        retrofit.create(GitApiService::class.java)
    }

    override val repository: MainRepository by lazy {
        MainRepositoryImpl(retrofitService)
    }

}