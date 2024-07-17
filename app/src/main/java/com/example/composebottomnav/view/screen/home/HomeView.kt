package com.example.composebottomnav.view.screen.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composebottomnav.view.navigation.EnumView
import com.example.composebottomnav.view.screen.news.NewView
import com.example.composebottomnav.view.screen.notification.NotificationView
import com.example.composebottomnav.view.screen.profile.ProfileView


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(navController: NavHostController) {
//	val postViewModel: ProfileViewModel = HiltViewModel()
	LaunchedEffect(key1 = Unit) {
		//postViewModel.getTodoList()
	}
	val selected = remember {
		mutableStateOf(Icons.Default.Home)
	}
	Scaffold(bottomBar = {
		BottomAppBar {
			IconButton(modifier = Modifier.weight(1f), onClick = {
				selected.value = Icons.Default.Home
				navController.navigate(EnumView.ProfileView.name) {
					popUpTo(0)
				}
			}) {
				Icon(
					Icons.Default.Home,
					contentDescription = null,
					tint = if (selected.value == Icons.Default.Home) Color.Red else Color.Black
				)
			}
			IconButton(modifier = Modifier.weight(1f), onClick = {
				selected.value = Icons.Default.Person
				navController.navigate(EnumView.NewView.name) {
					popUpTo(0)
				}
			}) {
				Icon(
					Icons.Default.Person,
					contentDescription = null,
					tint = if (selected.value == Icons.Default.Person) Color.Red else Color.Black
				)
			}
			IconButton(modifier = Modifier.weight(1f), onClick = {
				selected.value = Icons.Default.Notifications
				navController.navigate(EnumView.NotificationView.name) {
					popUpTo(0)
				}
			}) {
				Icon(
					Icons.Default.Notifications,
					contentDescription = null,
					tint = if (selected.value == Icons.Default.Notifications) Color.Red else Color.Black
				)
			}
		}
	}) {
		NavHost(navController = navController, startDestination = EnumView.ProfileView.name) {
			composable(EnumView.ProfileView.name) {
				ProfileView()
			}
			composable(EnumView.NewView.name) {
				NewView()
			}
			composable(EnumView.NotificationView.name) {
				NotificationView()
			}

		}
	}
}