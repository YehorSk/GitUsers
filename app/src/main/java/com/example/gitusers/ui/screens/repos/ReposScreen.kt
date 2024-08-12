package com.example.gitusers.ui.screens.repos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gitusers.ui.screens.repos.components.ReposList
import com.example.gitusers.ui.screens.users.UsersUiState
import com.example.retrofit_4.ui.screens.ErrorScreen
import com.example.retrofit_4.ui.screens.LoadingScreen


@Composable
fun ReposScreen(uiState: ReposUiState, retryAction: () -> Unit, modifier: Modifier = Modifier){
    when(uiState){
        is ReposUiState.Success -> ReposList(repos = uiState.repos, modifier = modifier)
        is ReposUiState.Loading -> LoadingScreen(modifier = modifier)
        is ReposUiState.Error -> ErrorScreen(retryAction = retryAction,modifier = modifier)
    }
}