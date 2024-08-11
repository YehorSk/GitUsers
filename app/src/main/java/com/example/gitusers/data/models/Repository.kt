package com.example.gitusers.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id: Int,
    val name: String,
    val url: String,
)
