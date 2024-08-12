package com.example.gitusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gitusers.navigation.MainScreen
import com.example.gitusers.ui.screens.users.UsersScreen
import com.example.gitusers.ui.screens.users.UsersUiState
import com.example.gitusers.ui.screens.users.UsersViewModel
import com.example.gitusers.ui.screens.users.components.UsersList
import com.example.gitusers.ui.theme.GitUsersTheme
import com.example.retrofit_4.ui.screens.ErrorScreen
import com.example.retrofit_4.ui.screens.LoadingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitUsersTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

