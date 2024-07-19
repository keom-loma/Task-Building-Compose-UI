package com.example.composebottomnav.view.screen.dashboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebottomnav.data.model.User
import com.example.composebottomnav.data.model.UserModel
import com.example.composebottomnav.data.repository.UserRepository
import com.example.composebottomnav.data.source.local.SharedPrefsBase
import com.example.composebottomnav.data.source.remote.LocalDatabase
import com.example.composebottomnav.view.screen.component.MyTextField
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun Dashboard() {


	Surface(
		modifier = Modifier

			.background(color = Color.Cyan)
			.padding(top = 85.dp)
	) {
		TabBar()
	}
}


@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun TabBar() {

	var selectedIndex by remember { mutableIntStateOf(0) }
	val listItemTab = listOf("SharePrefs", "RoomDatabase")


	Column(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		TabRow(
			selectedTabIndex = selectedIndex,
			modifier = Modifier
				.padding(vertical = 4.dp, horizontal = 8.dp)
				.clip(RoundedCornerShape(50.dp)),
			indicator = {
				Column(
					modifier = Modifier
						.background(color = Color.Gray)
						.clip(RoundedCornerShape(20.dp))
				) {
				}
			}
		) {
			listItemTab.forEachIndexed { index, s ->
				FilterChip(
					modifier = Modifier
						.wrapContentSize()
						.fillMaxWidth()
						.zIndex(1f)
						.align(Alignment.CenterHorizontally)
						.height(40.dp),
					selected = selectedIndex == index,
					border = if (selectedIndex == index) ChipDefaults.outlinedBorder else null,
					colors = FilterChipDefaults.filterChipColors(
						selectedContainerColor = Color.Red,
					),
					shape = if (selectedIndex == index) RoundedCornerShape(20.dp) else RoundedCornerShape(
						10.dp
					),
					onClick = { selectedIndex = index },
					label = {
						Text(
							text = s,
							textAlign = TextAlign.Center,
							modifier = Modifier.fillMaxWidth()
						)
					}
				)
			}
		}

		if (selectedIndex == 0) {
			TabBarViewIndex0()
		}
		if (selectedIndex == 1) {
			TabBarViewIndex1()
		}
	}
}


@Composable
fun TabBarViewIndex0() {
	val context = LocalContext.current
	val sharedPreferences = context.getSharedPreferences("MySharedPref", 0)

	val txtCtrlFirstName = remember { mutableStateOf("") }
	val txtCtrlLastName = remember { mutableStateOf("") }
	val txtCtrlFullName = remember { mutableStateOf("") }
	val txtCtrlNickName = remember { mutableStateOf("") }

	var firstNameError by remember { mutableStateOf(false) }
	val user = remember { mutableStateOf(User(0, "", "", "")) }
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = Color.Magenta)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(color = Color.White),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			MyTextField(txtCtrlFirstName.value, "Please enter your first name", onValueChange = {
				txtCtrlFirstName.value = it

			})
			MyTextField(txtCtrlLastName.value, "Please enter your last name", onValueChange = {
				txtCtrlLastName.value = it

			})
			MyTextField(
				txtCtrlFullName.value,
				"Please enter your full name",
				onValueChange = {
					txtCtrlFullName.value = it

				})
			Spacer(modifier = Modifier.padding(top = 12.dp))
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
					.height(48.dp)
					.background(color = Color.Green), onClick = {
					val user = User(
						firstName = txtCtrlFirstName.value,
						lastName = txtCtrlLastName.value,
						fullName = txtCtrlFullName.value
					)

					if (txtCtrlFirstName.value.isNotEmpty() && txtCtrlLastName.value.isNotEmpty() && txtCtrlFullName.value.isNotEmpty()) {
						SharedPrefsBase().saveData(sharedPreferences, "user", user)
						txtCtrlLastName.value = ""
						txtCtrlFirstName.value = ""
						txtCtrlFullName.value = ""
						Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
					}
				}) {
				Text(
					text = "Save data",
					style = TextStyle(color = Color.White)
				)
			}
			Spacer(modifier = Modifier.padding(top = 12.dp))
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
					.height(48.dp)
					.background(color = Color.Red), onClick = {
					user.value = SharedPrefsBase().getData(sharedPreferences, "user")
				}) {
				Text(
					text = "Retrieve data",
					style = TextStyle(color = Color.White)
				)
			}
			Spacer(modifier = Modifier.padding(top = 12.dp))
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
					.height(48.dp)
					.background(color = Color.Red),
				onClick = {
					SharedPrefsBase().removeData(sharedPreferences, "user")
					txtCtrlFullName.value = ""
					txtCtrlLastName.value = ""
					txtCtrlFirstName.value = ""

				}) {
				Text(
					text = "Remove Data",
					style = TextStyle(color = Color.White)
				)
			}
			Spacer(modifier = Modifier.padding(top = 12.dp))
			Text(text = " get data from shared : ${user.value.fullName}")
		}
	}
}

@Composable
fun TabBarViewIndex1(
	dashboardViewModel: DashboardViewModel = viewModel(
		factory = UserViewModelFactory(
			UserRepository(LocalDatabase.getDatabase(context = LocalContext.current).userDao())
		)
	)
) {

	val users by dashboardViewModel.allUsers.observeAsState(initial = emptyList())

	val coroutineScope = rememberCoroutineScope()
	var txtCtrlFirstName by remember { mutableStateOf("") }
	var txtCtrlLastName by remember { mutableStateOf("") }
	var txtCtrlEmailName by remember { mutableStateOf("") }
	val context = LocalContext.current

	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.background(color = Color.White)
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(color = Color.White),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Spacer(modifier = Modifier.padding(top = 12.dp))
			TextField(
				value = txtCtrlFirstName,
				onValueChange = {
					txtCtrlFirstName = it
				},
				label = { Text(text = "First Name") },
				placeholder = { Text(text = "Please enter your first name") },
				maxLines = 1,
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Email
				),
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
			)
			Spacer(modifier = Modifier.padding(top = 6.dp))
			TextField(
				value = txtCtrlLastName,
				onValueChange = {
					txtCtrlLastName = it
				},
				label = { Text(text = "Last Name") },
				placeholder = { Text(text = "Please enter your last name") },
				maxLines = 1,
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Email
				),
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
			)
			Spacer(modifier = Modifier.padding(top = 6.dp))
			TextField(
				value = txtCtrlEmailName,
				onValueChange = {
					txtCtrlEmailName = it
				},
				label = { Text(text = "Email") },
				placeholder = { Text(text = "Please enter your email") },
				maxLines = 1,
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Email
				),
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
			)
			Spacer(modifier = Modifier.padding(top = 12.dp))
			Button(
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 16.dp, end = 16.dp)
					.height(48.dp), onClick = {
					if (txtCtrlFirstName.isNotEmpty() && txtCtrlLastName.isNotEmpty() && txtCtrlEmailName.isNotEmpty()) {
						coroutineScope.launch {
							dashboardViewModel.insert(
								UserModel(
									firstName = txtCtrlFirstName,
									lastName = txtCtrlLastName,
									email = txtCtrlEmailName
								)
							)
							txtCtrlFirstName = ""
							txtCtrlLastName = ""
							txtCtrlEmailName = ""
						}
					} else {
						Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
					}


				}) {
				Text(text = "Insert Data", style = TextStyle(color = Color.White))
			}
			Spacer(modifier = Modifier.padding(top = 8.dp))
			if (users.isEmpty()) Text(
				text = "No data ",
				modifier = Modifier.padding(100.dp)
			) else LazyColumn() {
				items(users.size) {
					Surface(
						modifier = Modifier
							.fillMaxWidth()
							.padding(start = 16.dp, end = 16.dp, top = 12.dp)

							.clip(RoundedCornerShape(12.dp)),

						onClick = {
							coroutineScope.launch {
								dashboardViewModel.deleteUserById(users[it].id)
							}
						}

					) {
						Row(
							verticalAlignment = Alignment.CenterVertically,
							horizontalArrangement = Arrangement.SpaceBetween,
							modifier = Modifier.background(color = if (users[it].id % 2 == 0) Color.Gray else Color.LightGray)
						) {

							Column {
								Text(
									text = " ${users[it].firstName} ${users[it].lastName}",
									modifier = Modifier.padding(12.dp)
								)
								Text(
									text = " ${users[it].email}",
									modifier = Modifier.padding(
										start = 12.dp,
										end = 12.dp,
										bottom = 12.dp
									)
								)
							}
							Text(
								text = "Id: ${users[it].id}",
								modifier = Modifier.padding(12.dp)
							)
						}
					}
				}
			}

		}
	}
}