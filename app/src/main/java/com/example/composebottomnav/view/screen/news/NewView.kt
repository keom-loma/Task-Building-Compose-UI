package com.example.composebottomnav.view.screen.news

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composebottomnav.R
import com.example.composebottomnav.view.navigation.EnumView

@Composable
fun NewView(navController: NavController) {
	Surface(modifier = Modifier.padding(top = 90.dp)) {
		Column(
			modifier = Modifier.padding(
				8.dp
			)
		) {

			Column(
				modifier = Modifier
					.padding(
						top = 16.dp,
						bottom = 16.dp,
						start = 16.dp,
						end = 16.dp
					)
					.height(170.dp)
			) {
				Box(
					modifier = Modifier
						.fillMaxSize()
						.height(100.dp)
						.clip(RoundedCornerShape(12.dp))
						.background(color = Color.Red)

				) {
					Box {
						AsyncImage(
							model = ImageRequest.Builder(LocalContext.current)
								.data("https://i.pinimg.com/originals/fd/09/94/fd099408f7d2823899b828cda6a13dc9.jpg")
								.crossfade(true)
								.build(),
							placeholder = painterResource(R.drawable.placholder),
							contentDescription = stringResource(R.string.dec),
							contentScale = ContentScale.Crop,
							modifier = Modifier.fillMaxSize()
						)
					}
					Column(
						modifier = Modifier.padding(20.dp)
					) {
						Row {
							Text(
								text = "ABA Bank",
								style = TextStyle(
									color = Color.White,
									fontSize = 16.sp,
									fontWeight = FontWeight.W500
								),
							)
						}
						Spacer(modifier = Modifier.padding(top = 20.dp))
						Column(
							modifier = Modifier.fillMaxSize(),
							horizontalAlignment = Alignment.CenterHorizontally
						) {
							HorizontalDivider()
							Spacer(modifier = Modifier.padding(top = 6.dp))
							Text(
								text = "xxx-xxx-xxx-xxx-xxx",
								style = TextStyle(
									color = Color.White,
									fontSize = 16.sp,
									fontWeight = FontWeight.W500
								),
							)
							Spacer(modifier = Modifier.padding(top = 6.dp))
							Column(
								modifier = Modifier.fillMaxSize(),
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								Text(
									text = "Year-month-day",
									style = TextStyle(
										color = Color.White,
										fontSize = 14.sp,
										fontWeight = FontWeight.W400
									),
								)
								Spacer(modifier = Modifier.padding(top = 6.dp))
								Column(
									modifier = Modifier.fillMaxSize(),
									horizontalAlignment = Alignment.Start
								) {
									Spacer(modifier = Modifier.padding(top = 12.dp))
									Row(modifier = Modifier.fillMaxSize()) {

										Box(
											modifier = Modifier
												.padding(start = 6.dp)
												.weight(1f)
										) {
											Text(
												text = "Jupyter",
												style = TextStyle(
													color = Color.White,
													fontSize = 16.sp,
													fontWeight = FontWeight.W500
												),
											)
										}
										Box {
											Text(
												text = "CSS",
												style = TextStyle(
													color = Color.White,
													fontSize = 18.sp,
													fontWeight = FontWeight.W500
												),
											)
										}
									}
								}

							}

						}

					}

				}
			}
			Spacer(modifier = Modifier.padding(top = 8.dp))
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(text = "Transaction")
				TextButton(onClick = {
					Log.i("TAG Information:", "NewView: ${navController.currentDestination?.route}")
					navController.navigate(EnumView.DashboardView.name)
				}) {
					Text(text = "See more")
				}
			}
			LazyColumn {
				items(110) {
					TransactionItem()
				}
			}
		}

	}
}

@Preview(showBackground = true)
@Composable
fun TransactionItem() {
	Box(
		modifier = Modifier
			.padding(8.dp)
			.clip(RoundedCornerShape(10.dp))
			.background(color = Color.LightGray)
	) {
		Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
			Image(imageVector = Icons.Default.Person, contentDescription = "transaction")
			Column(modifier = Modifier.fillMaxWidth()) {

				Text(text = "July, 10 2024")
				Spacer(modifier = Modifier.padding(top = 6.dp))
				Text(text = "July, 10 2024")
			}
		}
	}
}