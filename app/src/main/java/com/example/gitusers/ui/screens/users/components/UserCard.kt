package com.example.gitusers.ui.screens.users.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gitusers.R
import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.models.User
import com.example.gitusers.ui.theme.GitUsersTheme

@Composable
fun UserCard(user: User,onClick: () -> Unit, modifier: Modifier = Modifier){

    Card(
        modifier = modifier
            .animateContentSize()
            .padding(8.dp)
            .height(100.dp)
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxHeight()
                    .clip(CircleShape),
                model = user.avatarUrl,
                contentDescription = user.login,
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.ic_broken_image)
            )
            Text(
                text = user.login,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun UserCardPreview(modifier: Modifier = Modifier){
    UserCard(
        User("mojombo",1,"",""),
        {}
    )
}

@Preview
@Composable
fun UsersCardListPreview(){
    GitUsersTheme {
        val mockData = List(20){User("Name_$it",it,"","")}
        UsersList(users = mockData,{},{})
    }
}