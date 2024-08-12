package com.example.gitusers.ui.screens.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitusers.data.models.User
import com.example.gitusers.ui.screens.users.components.UsersList
import com.example.retrofit_4.ui.screens.ErrorScreen
import com.example.retrofit_4.ui.screens.LoadingScreen


@Composable
fun UsersScreen(uiState: UsersUiState, retryAction: () -> Unit, onClick: (User) -> Unit, modifier: Modifier = Modifier){
    when(uiState){
        is UsersUiState.Success -> UsersList(users = uiState.users, onClick ,modifier = modifier)
        is UsersUiState.Loading -> LoadingScreen(modifier = modifier)
        is UsersUiState.Error -> ErrorScreen(retryAction = retryAction,modifier = modifier)
    }
}