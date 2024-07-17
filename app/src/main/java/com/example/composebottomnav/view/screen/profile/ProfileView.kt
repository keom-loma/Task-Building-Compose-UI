package com.example.composebottomnav.view.screen.profile

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
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composebottomnav.R

@Composable
fun ProfileView() {
	Surface(modifier = Modifier.padding(bottom = 100.dp)) {
		LazyColumn {
			items(10) {
				ItemFriend()
			}
		}
	}
}

@Composable
fun ItemFriend() {
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
			Column {
				Box(
				) {
					Column {
						Text(text = "Send you a friend request")
						Spacer(modifier = Modifier.height(8.dp))
						Text(text = "Others mutual friends")
					}
				}
			}
			TextButton(onClick = { /*TODO*/ }) {
				Text(text = "Add")
			}
		}
	}
}