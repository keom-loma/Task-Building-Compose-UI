package com.example.composebottomnav.view.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composebottomnav.R

@Composable
fun ProfileView() {
	val viewModel: ProfileViewModel = hiltViewModel()

	Surface(modifier = Modifier.padding(bottom = 100.dp)) {
		val todos = viewModel.todos.collectAsState()

		println("Information todos on profile view: ${todos.value.size}")
		LazyColumn {
			items(viewModel.todos.value.size) {
				ItemFriend(
					todos.value[it].completed,
					todos.value[it].id,
					todos.value[it].title,
					todos.value[it].userId.toString()
				)
			}
		}
	}
}

@Composable
fun ItemFriend(isCompleted: Boolean, id: Int, title: String, description: String) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp, vertical = 8.dp)

	) {
		Row(
			modifier = Modifier
				.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(modifier = Modifier.height(50.dp)) {
				AsyncImage(
					model = ImageRequest.Builder(LocalContext.current)
						.data("https://imgs.search.brave.com/mFfJ27YtTaGHPYRIcQ0B9A4v7b0ld7pNd7yrYdGAsp4/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90NC5m/dGNkbi5uZXQvanBn/LzAzLzAyLzUzLzAz/LzM2MF9GXzMwMjUz/MDMwN19Jd0VuZDJq/Vmd0ZEE4dmJobGtO/cGJBUEIwcDBjNnRL/Ri5qcGc")
						.crossfade(true)
						.build(),
					placeholder = painterResource(R.drawable.placholder),
					contentDescription = stringResource(R.string.dec),
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.clip(CircleShape)
						.size(50.dp)
				)
			}
			Box(
				modifier = Modifier
					.weight(1f)
					.height(48.dp)

			) {
				Column(
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.Start
				) {
					Box(
						modifier = Modifier
							.weight(1f)
							.padding(end = 16.dp)
					) {
						Column(
							modifier = Modifier.padding(start = 16.dp),

							) {
							Text(
								text = title, style = TextStyle(
									color = Color.Black
								), maxLines = 1,
								overflow = TextOverflow.Ellipsis
							)
							Spacer(modifier = Modifier.height(8.dp))
							Text(text = description)
						}
					}
				}
			}
			Box(
				modifier = Modifier
					.clip(RoundedCornerShape(16.dp)) // Apply the clipping shape first
					.background(color = if (isCompleted) Color.Green else Color.Red) // Apply the background color after the clipping shape
					.padding(start = 8.dp, top = 4.dp, bottom = 4.dp, end = 8.dp)
					.clickable {
						println("Information todo id: $id")
					}
			) {
				Text(
					text = if (isCompleted) "Remove" else "Add",
					style = TextStyle(color = Color.White)
				)
			}

		}
	}
}