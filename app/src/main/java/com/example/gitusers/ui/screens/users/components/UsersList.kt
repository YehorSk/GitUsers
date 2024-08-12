package com.example.gitusers.ui.screens.users.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.gitusers.data.models.User
import com.example.gitusers.ui.theme.GitUsersTheme

@Composable
fun UsersList(users: List<User>,onClick: (User) ->Unit,modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
    ) {
        items(users){
            UserCard(user = it, onClick = { onClick(it) })
        }
    }
}

@Preview
@Composable
fun UsersListPreview(){
    GitUsersTheme {
        val mockData = List(20){User("Name_$it",it,"","")}
        UsersList(users = mockData,{})
    }
}