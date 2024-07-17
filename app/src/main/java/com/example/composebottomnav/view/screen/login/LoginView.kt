package com.example.composebottomnav.view.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun LoginView() {
	val keyboardManager = LocalSoftwareKeyboardController.current
	Surface(onClick = {
		keyboardManager?.hide()
	}) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(color = Color.Cyan)
		) {
		}
		Box(modifier = Modifier.fillMaxSize()) {
			Column(
				modifier = Modifier.align(Alignment.Center),
				verticalArrangement = Arrangement.Bottom,
				horizontalAlignment = Alignment.End
			) {
			}
		}

	}
}