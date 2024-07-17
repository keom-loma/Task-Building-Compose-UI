package com.example.composebottomnav

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composebottomnav.ui.theme.ComposeBottomNavTheme
import com.example.composebottomnav.view.navigation.EnumView
import com.example.composebottomnav.view.navigation.NavigationBase

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			// val viewModel = HiltViewModel()
			ComposeBottomNavTheme {
				Surface(modifier = Modifier.fillMaxSize()) {
					NavigationBase()
				}
			}
		}
	}
}

@Composable
fun TestView(navController: NavController) {
	Surface(modifier = Modifier.fillMaxSize()) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(text = "Test View")
			Button(onClick = {
				navController.navigate(EnumView.HomeView.name)
			}) {

				Text(text = "Click to dashboard")

			}
		}
	}
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MyBottomBar() {
	val navigationController = rememberNavController()
	val context = LocalContext.current
	val selected = remember {
		mutableStateOf(Icons.Default.Home)
	}
	Scaffold(
		modifier = Modifier.fillMaxSize(), bottomBar = {
			BottomAppBar(containerColor = MaterialTheme.colorScheme.primary) {
				IconButton(
					onClick = {
						selected.value = Icons.Default.Home
						navigationController.navigate(EnumView.HomeView.name) {
							popUpTo(0)
						}
					},
					modifier = Modifier.weight(1f)
				) {
					Icon(
						Icons.Default.Home,
						contentDescription = null,
						modifier = Modifier.size(20.dp),
						tint = if (selected.value == Icons.Default.Home) Color.Red else Color.White
					)
				}
				IconButton(
					onClick = {
						selected.value = Icons.Default.AccountCircle
						navigationController.navigate(EnumView.NewView.name) {
							popUpTo(0)
						}
					},
					modifier = Modifier.weight(1f)
				) {
					Icon(
						Icons.Default.AccountCircle,
						contentDescription = null,
						modifier = Modifier.size(20.dp),
						tint = if (selected.value == Icons.Default.AccountCircle) Color.Red else Color.White
					)
				}
				IconButton(
					onClick = {
						selected.value = Icons.Default.Person
						navigationController.navigate(EnumView.ProfileView.name) {
							popUpTo(0)
						}
					},
					modifier = Modifier.weight(1f)
				) {
					Icon(
						Icons.Default.Person,
						contentDescription = null,
						modifier = Modifier.size(20.dp),
						tint = if (selected.value == Icons.Default.Person) Color.Red else Color.White
					)
				}
				IconButton(
					onClick = {
						selected.value = Icons.Default.Notifications
						navigationController.navigate(EnumView.NotificationView.name) {
							popUpTo(0)
						}
					},
					modifier = Modifier.weight(1f)
				) {
					Icon(
						Icons.Default.Notifications,
						contentDescription = null,
						modifier = Modifier.size(20.dp),
						tint = if (selected.value == Icons.Default.Notifications) Color.Red else Color.White
					)
				}
				IconButton(
					onClick = {
						selected.value = Icons.Default.Settings
						navigationController.navigate(EnumView.SettingView.name) {
							popUpTo(0)
						}
					},
					modifier = Modifier.weight(1f)
				) {
					Icon(
						Icons.Default.Settings,
						contentDescription = null,
						modifier = Modifier.size(20.dp),
						tint = if (selected.value == Icons.Default.Settings) Color.Red else Color.White
					)
				}
			}
		}
	) { _ ->
		// NavigationBase()
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	ComposeBottomNavTheme {
		Greeting("Android")
	}
}