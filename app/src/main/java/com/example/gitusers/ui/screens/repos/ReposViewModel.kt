package com.example.gitusers.ui.screens.repos

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
import com.example.gitusers.data.models.Repository
import com.example.gitusers.data.repository.MainRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ReposUiState{
    data class Success(val repos: List<Repository>) : ReposUiState
    object Error: ReposUiState
    object Loading: ReposUiState
}

class ReposViewModel(
    val repository: MainRepository
) : ViewModel() {

    var reposUiState: ReposUiState by mutableStateOf(ReposUiState.Loading)

    fun getRepos(name: String) {
        reposUiState = ReposUiState.Loading
       viewModelScope.launch {
           reposUiState = try {
                ReposUiState.Success(repository.getUserRepos(name))
           }catch (e: IOException){
                ReposUiState.Error
           }
       }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationContainer)
                ReposViewModel(repository = application.container.repository)
            }
        }
    }

}