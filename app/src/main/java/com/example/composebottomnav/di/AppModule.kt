//package com.example.composebottomnav.di
//
//import com.example.composebottomnav.data.service.TodoService
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//	@Provides
//	@Singleton
//	fun TodoProvider(): TodoService {
//		return Retrofit.Builder()
//			.baseUrl("https://jsonplaceholder.typicode.com")
//			.addConverterFactory(GsonConverterFactory.create())
//			.build()
//			.create(TodoService::class.java)
//	}
//}