package com.example.composebottomnav.data.service

import com.example.composebottomnav.data.model.TodoModelItem
import retrofit2.http.GET

interface TodoService {
	@GET("/todos")
	suspend fun getTodos(): List<TodoModelItem>
}