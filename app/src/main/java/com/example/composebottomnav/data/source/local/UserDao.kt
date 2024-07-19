package com.example.composebottomnav.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composebottomnav.data.model.UserModel
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(user: UserModel)


	@Query("DELETE FROM user_table")
	suspend fun deleteAll()


	@Query("DELETE FROM user_table WHERE id = :id")
	suspend fun delete(id: Int)

	@Query("SELECT * FROM user_table ORDER BY firstName ASC")
	fun getAll(): Flow<List<UserModel>>
}