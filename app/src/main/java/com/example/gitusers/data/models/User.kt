package com.example.gitusers.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("repos_url")
    val reposUrl: String,
)
