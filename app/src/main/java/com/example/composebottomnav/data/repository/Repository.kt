package com.example.composebottomnav.data.repository

import com.example.composebottomnav.data.model.TodoModelItem
import com.example.composebottomnav.data.service.TodoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Repository constructor(private val todoService: TodoService) {

	suspend fun getTodos(): Flow<List<TodoModelItem>> = flow {
		var result = todoService.getTodos()
		emit(result)
	}
}