package com.example.gitusers.ui.screens.repos.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gitusers.data.models.Repository
import com.example.gitusers.navigation.TopBar


@Composable
fun ReposList(repos: List<Repository>, loadMore: () -> Unit, modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
    ) {
        items(repos){
            RepoCard(repository = it, onClick = { /*TODO*/ })
        }
        item {
            Button(
                onClick = loadMore,
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Text(text = "Load More")
            }
        }
    }
}