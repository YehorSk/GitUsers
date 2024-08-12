package com.example.gitusers.ui.screens.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.gitusers.ApplicationContainer
import com.example.gitusers.data.models.User
import com.example.gitusers.data.repository.MainRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UsersUiState{
    data class Success(val users: List<User>) : UsersUiState
    object Loading : UsersUiState
    object Error : UsersUiState
}

class UsersViewModel(private val mainRepository: MainRepository) : ViewModel(){

    var usersUiState : UsersUiState by mutableStateOf(UsersUiState.Loading)

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            usersUiState = try {
                UsersUiState.Success(mainRepository.getUsers())
            }catch (e: IOException){
                UsersUiState.Error
            }
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationContainer)
                UsersViewModel(mainRepository = application.container.repository)
            }
        }
    }

}