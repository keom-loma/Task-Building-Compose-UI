package com.example.composebottomnav.view.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun Dashboard() {
	var selectedIndex by remember { mutableIntStateOf(0) }
	val listItemTab = listOf("Coins", "Subscriptions")


	Surface(
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Cyan)
			.padding(top = 85.dp)
	) {
		Column {
			TabRow(
				selectedTabIndex = selectedIndex,
				modifier = Modifier
					.padding(vertical = 4.dp, horizontal = 8.dp)
					.clip(RoundedCornerShape(50))
					.padding(1.dp),
				indicator = { tabPositions ->
					Column(
						modifier = Modifier
							.fillMaxSize()
							.padding(10.dp)
							.background(
								MaterialTheme.colorScheme.secondaryContainer,
								FilterChipDefaults.shape,
							)
					) {

					}
				}
			) {
				listItemTab.forEachIndexed { index, s ->

					FilterChip(
						modifier = Modifier
							.wrapContentSize()
							.zIndex(2f),
						selected = false,
						border = null,
						onClick = { selectedIndex = index },
						label = {
							Text(text = s, textAlign = TextAlign.Center)
						}
					)
					/*	Tab(
							modifier = if (selected) Modifier
								.clip(RoundedCornerShape(50))
								.background(
									Color.White
								)
							else Modifier
								.clip(RoundedCornerShape(50))
								.background(
									Color(
										0xff1E76DA
									)
								),
							selected = selected,
							onClick = {
								selectedIndex = index
							}) {
							Text(text = s)
						}*/
				}
			}
		}
	}
}