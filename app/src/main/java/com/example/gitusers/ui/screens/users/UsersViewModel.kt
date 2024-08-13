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
import com.example.gitusers.data.Constants
import com.example.gitusers.data.models.User
import com.example.gitusers.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UsersUiState{
    data class Success(val users: List<User>) : UsersUiState
    object Loading : UsersUiState
    object Error : UsersUiState
}

class UsersViewModel(private val mainRepository: MainRepository) : ViewModel(){

    var usersUiState : UsersUiState by mutableStateOf(UsersUiState.Loading)
    var usersList = MutableStateFlow<List<User>>(listOf())


    private var since = 0
    private var perPage = Constants.PER_PAGE

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            usersUiState = try {
                val result = mainRepository.getUsers(since = since, perPage = perPage)
                usersList.update {
                    it + result
                }
                UsersUiState.Success(usersList.value)
            }catch (e: IOException){
                UsersUiState.Error
            }
            if(usersUiState is UsersUiState.Success){
                since += perPage
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