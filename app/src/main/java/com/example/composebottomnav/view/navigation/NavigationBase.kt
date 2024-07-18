package com.example.composebottomnav.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebottomnav.TestView
import com.example.composebottomnav.view.screen.dashboard.Dashboard
import com.example.composebottomnav.view.screen.home.HomeView

@Composable
fun NavigationBase() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = EnumView.TEST.name) {
		composable(EnumView.TEST.name) { TestView(navController) }
		composable(EnumView.HomeView.name) {
			val navHostCtrl = rememberNavController()
			HomeView(navController = navHostCtrl)
		}
		composable(EnumView.DashboardView.name) {
			Dashboard()
		}
	}
}

