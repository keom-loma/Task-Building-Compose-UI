package com.example.composebottomnav.view.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebottomnav.data.model.TodoModelItem
import com.example.composebottomnav.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
	private val _todos = MutableStateFlow<List<TodoModelItem>>(emptyList())
	val todos: StateFlow<List<TodoModelItem>> = _todos.asStateFlow()
	private val _isLoading = MutableLiveData<Boolean>(false)
	val isLoading: LiveData<Boolean> = _isLoading

	init {
		getTodoList()
	}

	private fun getTodoList() {
		viewModelScope.launch {
			runCatching {
				withContext(Dispatchers.Main) {
					_isLoading.value = true
				}
				withContext(Dispatchers.IO) {
					repository.getTodos().collect {
						withContext(Dispatchers.Main) {
							_todos.value = it
							_isLoading.value = true
							println("Information todos: ${it.size}")
						}
					}
				}
			}.onFailure {
				_isLoading.value = false
			}.onSuccess {
				withContext(Dispatchers.Main) {
					_isLoading.value = false
				}
			}
		}
	}
}