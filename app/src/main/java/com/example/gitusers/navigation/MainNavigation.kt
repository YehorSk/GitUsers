package com.example.gitusers.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.gitusers.R
import com.example.gitusers.data.models.User
import com.example.gitusers.ui.screens.repos.ReposScreen
import com.example.gitusers.ui.screens.repos.ReposViewModel
import com.example.gitusers.ui.screens.users.UsersScreen
import com.example.gitusers.ui.screens.users.UsersViewModel
import kotlinx.serialization.Serializable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(canNavigateBack: Boolean,navigateUp: () -> Unit = {},modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(text = "GitUsers")
        },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
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
    NavHost(
        navController = navController,
        startDestination = ScreenUsers
    ){
        composable<ScreenUsers> {
            UsersScreen(
                uiState = usersUiState,
                retryAction = viewModel::getUsers,
                modifier = Modifier
                    .fillMaxSize(),
                loadMore = viewModel::getUsers,
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
                goBack = { navController.navigateUp() },
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}

@Serializable
object ScreenUsers

@Serializable
data class ScreenRepos(
    val userName: String
)