package com.example.composebottomnav.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebottomnav.view.screen.profile.ProfileView

@Composable
fun NavigationBottom() {
	val navHostCtrl = rememberNavController()
	NavHost(navController = navHostCtrl, startDestination = EnumView.ProfileView.name) {
		composable(EnumView.ProfileView.name) { ProfileView() }
//		composable(EnumView.NewView.name) {
//			val navController = rememberNavController()
//			NewView(navController = navController)
//		}
		// composable(EnumView.NotificationView.name) { NotificationView() }
	}
}