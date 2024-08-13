package com.example.gitusers.ui.screens.users.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gitusers.data.models.User
import com.example.gitusers.ui.theme.GitUsersTheme

@Composable
fun UsersList(users: List<User>,loadMore: () -> Unit,onClick: (User) ->Unit,modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
    ) {
        items(users){
            UserCard(user = it, onClick = { onClick(it) })
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

@Preview
@Composable
fun UsersListPreview(){
    GitUsersTheme {
        val mockData = List(20){User("Name_$it",it,"","")}
        UsersList(users = mockData,{},{})
    }
}