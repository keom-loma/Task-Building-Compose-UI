package com.example.composebottomnav.view.screen.notification

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@ExperimentalFoundationApi
@Composable
fun NotificationView() {
	val coroutineScope = rememberCoroutineScope()
	val listItemTab = listOf("Tab 1", "Tab 2", "Tab 3 ", "Tab 4", "Tab 5", "Tab 6", "Tab 7")
	val pagerState = rememberPagerState()

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(top = 90.dp),
		verticalArrangement = Arrangement.Top,
		horizontalAlignment = Alignment.CenterHorizontally

	) {
		ScrollableTabRow(
			selectedTabIndex = pagerState.currentPage,
			edgePadding = 20.dp,
			contentColor = Color(0xFF362C28),
			indicator = {
				CustomGradientTabIndicator(it, pagerState.currentPage)
			}
		) {
			listItemTab.forEachIndexed { index, s ->
				Tab(
					unselectedContentColor = Color.Black,
					selectedContentColor = Color.Blue,
					selected = pagerState.currentPage == index,

					onClick = {
						coroutineScope.launch {
							pagerState.animateScrollToPage(index)
						}

					},
					text = {
						Box(
							modifier = Modifier
								.clip(RoundedCornerShape(2.0.dp))
								.fillMaxWidth()

						) {
							Text(text = s)
						}
					}
				)
			}
		}
		HorizontalPager(
			count = listItemTab.size,
			state = pagerState,
			modifier = Modifier
				.weight(1f)
		) { page ->
			TabContent("Content for ${listItemTab[page]}")
		}
		/*
				when (selected1) {
					0 -> TabBarContent(content = "Tab 1")
					1 -> TabBarContent(content = "Tab 2")
					2 -> TabBarContent(content = "Tab 3")
					3 -> TabBarContent(content = "Tab 4")
					4 -> TabBarContent(content = "Tab 5")
					5 -> TabBarContent(content = "Tab 6")
					6 -> TabBarContent(content = "Tab 7")
				}
		*/
	}
}

@Composable
fun CustomGradientTabIndicator(tabPositions: List<TabPosition>, selectedTabIndex: Int) {
	val currentTabPosition = tabPositions[selectedTabIndex]

	Box(
		Modifier
			.fillMaxSize()
			.wrapContentSize(align = Alignment.BottomStart)
			.offset(x = currentTabPosition.left)
			.width(currentTabPosition.width)
			.padding(horizontal = 22.dp)
			.height(4.dp)
			.clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
			.background(color = Color.Blue)
	)
}

@Composable
fun TabContent(content: String) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.LightGray)
			.padding(
				top = 100.dp
			),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = content,
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.bodyLarge
		)
	}
}

@Composable
fun TabBarContent(content: String) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.LightGray),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = content,
			textAlign = TextAlign.Center,
			style = MaterialTheme.typography.bodyLarge
		)
	}
}