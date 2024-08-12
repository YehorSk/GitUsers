package com.example.gitusers.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.gitusers.data.models.User
import com.example.gitusers.ui.screens.repos.ReposScreen
import com.example.gitusers.ui.screens.repos.ReposViewModel
import com.example.gitusers.ui.screens.users.UsersScreen
import com.example.gitusers.ui.screens.users.UsersViewModel
import kotlinx.serialization.Serializable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Users")
        }
    )
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val viewModel : UsersViewModel = viewModel(factory = UsersViewModel.Factory)
    val reposViewModel : ReposViewModel = viewModel(factory = ReposViewModel.Factory)
    val usersUiState = viewModel.usersUiState
    val reposUiState = reposViewModel.reposUiState
    Scaffold(
        topBar = {TopBar()}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenUsers
        ){
            composable<ScreenUsers> {
                UsersScreen(
                    uiState = usersUiState,
                    retryAction = viewModel::getUsers,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    onClick = {
                        navController.navigate(
                            ScreenRepos(
                                userName = it.login
                            )
                        )
                        reposViewModel.getRepos(it.login)
                    }
                )
            }
            composable<ScreenRepos> {
                val args = it.toRoute<ScreenRepos>()
                ReposScreen(
                    uiState = reposUiState,
                    retryAction = { reposViewModel.getRepos(args.userName) },
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                )
            }
        }

    }
}

@Serializable
object ScreenUsers

@Serializable
data class ScreenRepos(
    val userName: String
)