package com.example.composebottomnav.view.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composebottomnav.data.model.UserModel
import com.example.composebottomnav.data.repository.UserRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val userRepository: UserRepository) : ViewModel() {
	val allUsers = userRepository.allUser.asLiveData()

	fun insert(user: UserModel) = viewModelScope.launch {
		userRepository.insert(user)
	}

	fun delete(user: UserModel) = viewModelScope.launch {
		userRepository.remove(user)
	}

	fun deleteUserById(id: Int) = viewModelScope.launch {
		userRepository.deleteUser(id)
	}

}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return DashboardViewModel(repository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
