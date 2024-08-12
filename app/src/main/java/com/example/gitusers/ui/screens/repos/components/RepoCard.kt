package com.example.gitusers.ui.screens.repos.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gitusers.data.models.Repository

@Composable
fun RepoCard(repository: Repository,onClick: ()->Unit, modifier: Modifier = Modifier){
    var cardVisible by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier.animateContentSize().padding(8.dp).clickable {
            cardVisible = !cardVisible
            onClick()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleLarge,
            )
            if(cardVisible){
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = repository.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun RepoCardPreview(modifier: Modifier = Modifier){
    RepoCard(repository = Repository(
        1,
        "30daysoflaptops.github.io",
        "",
        "Destroy your Atom editor, Asteroids style!"
    ),{})
}