package com.example.composebottomnav.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserModel(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val firstName: String,
	val lastName: String,
	val email: String,
)
