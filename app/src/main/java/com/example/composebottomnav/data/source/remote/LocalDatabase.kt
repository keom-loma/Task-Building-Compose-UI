package com.example.composebottomnav.data.source.remote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composebottomnav.data.model.UserModel
import com.example.composebottomnav.data.source.local.UserDao

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
	abstract fun userDao(): UserDao

	companion object {
		@Volatile
		private var instance: LocalDatabase? = null


		fun getDatabase(context: Context): LocalDatabase {
			val tempInstance = instance
			if (tempInstance != null) {
				return tempInstance
			}
			synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					LocalDatabase::class.java,
					"user_database"
				).build()
				instance.userDao()
				return instance
			}
		}
	}
}