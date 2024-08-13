package com.example.gitusers.ui.screens.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitusers.data.models.User
import com.example.gitusers.navigation.TopBar
import com.example.gitusers.ui.screens.users.components.UsersList
import com.example.retrofit_4.ui.screens.ErrorScreen
import com.example.retrofit_4.ui.screens.LoadingScreen


@Composable
fun UsersScreen(uiState: UsersUiState, loadMore: () -> Unit, retryAction: () -> Unit, onClick: (User) -> Unit, modifier: Modifier = Modifier){
    Scaffold(
        topBar = { TopBar(canNavigateBack = false, navigateUp = { }) }
    ) { innerPadding ->
        when(uiState){
            is UsersUiState.Success -> UsersList(users = uiState.users, loadMore = loadMore, onClick ,modifier = modifier.padding(innerPadding))
            is UsersUiState.Loading -> LoadingScreen(modifier = modifier.padding(innerPadding))
            is UsersUiState.Error -> ErrorScreen(retryAction = retryAction,modifier = modifier.padding(innerPadding))
        }
    }
}