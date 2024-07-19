package com.example.composebottomnav.di

import android.content.Context
import androidx.room.Room
import com.example.composebottomnav.data.service.TodoService
import com.example.composebottomnav.data.source.local.UserDao
import com.example.composebottomnav.data.source.remote.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	@Singleton
	fun TodoProvider(): TodoService {
		return Retrofit.Builder()
			.baseUrl("https://jsonplaceholder.typicode.com")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(TodoService::class.java)
	}
}


@Module
@InstallIn(SingletonComponent::class)
object AppModuleRoomDatabaseLocal {
	@Provides
	@Singleton
	fun appProviderDatabase(@ApplicationContext context: Context): LocalDatabase {
		return Room.databaseBuilder(
			context = context,
			LocalDatabase::class.java,
			"user.db"
		).build()
	}


	@Provides
	fun providerUserDao(userDatabase: LocalDatabase): UserDao {
		return userDatabase.userDao()
	}
}