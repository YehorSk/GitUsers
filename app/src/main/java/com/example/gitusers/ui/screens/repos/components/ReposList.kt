package com.example.gitusers.ui.screens.repos.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gitusers.data.models.Repository


@Composable
fun ReposList(repos: List<Repository>, modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
    ) {
        items(repos){
            RepoCard(repository = it, onClick = { /*TODO*/ })
        }
    }
}