package com.example.composebottomnav.data.repository

import com.example.composebottomnav.data.model.UserModel
import com.example.composebottomnav.data.source.local.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
	val allUser: Flow<List<UserModel>> = userDao.getAll()
	suspend fun insert(user: UserModel) {
		userDao.insert(user)
	}

	suspend fun remove(user: UserModel) {
		userDao.insert(user)
	}

	suspend fun deleteUser(userId: Int) {
		userDao.delete(id = userId)
	}
}