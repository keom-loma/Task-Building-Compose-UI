package com.example.composebottomnav.data.source.local

import android.content.SharedPreferences
import com.example.composebottomnav.data.model.User
import com.google.gson.Gson

class SharedPrefsBase {
	fun saveData(sharedPreferences: SharedPreferences, keyValue: String = "user", user: User) {
		val editor = sharedPreferences.edit()
		val json = Gson().toJson(user)
		editor.putString(keyValue, json)
		editor.apply()
	}

	fun getData(sharedPreferences: SharedPreferences, keyValue: String = "user"): User {
		val json = sharedPreferences.getString(keyValue, "") ?: ""
		return if (json.isNotEmpty()) {
			Gson().fromJson(json, User::class.java)
		} else {
			User(0, "", "", "")
		}
	}

	fun removeData(sharedPreferences: SharedPreferences, keyValue: String = "user") {
		val editor = sharedPreferences.edit()
		editor.remove(keyValue)
		editor.apply()
	}

}