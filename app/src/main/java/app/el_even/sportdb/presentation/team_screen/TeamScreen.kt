package app.el_even.sportdb.presentation.team_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TeamScreen(viewModel: TeamViewModel = hiltViewModel()) {

	val state = viewModel.state.value
	Box(modifier = Modifier.fillMaxWidth()) {
		state.team?.let { team ->
			LazyColumn(
				modifier = Modifier.fillMaxSize(),
				contentPadding = PaddingValues(20.dp)
			) {
				item {
					GlideImage(
						imageModel = team.banner,
						modifier = Modifier.fillMaxSize()
					)
					Spacer(modifier = Modifier.height(15.dp))
					Text(text = "Name: ${team.fullName}")
					Spacer(modifier = Modifier.height(15.dp))
					Text(text = "League: ${team.league}")
					Spacer(modifier = Modifier.height(15.dp))
					Text(text = "Nickname: ${team.nickname}")
					Spacer(modifier = Modifier.height(15.dp))
					Text(text = "Country: ${team.location}")
					Spacer(modifier = Modifier.height(15.dp))
					Text(text = team.description)
				}
			}
		}

		if (state.error.isNotBlank()) {
			Text(
				text = state.error,
				color = MaterialTheme.colors.error,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 20.dp)
					.align(Alignment.Center)
			)
		}
		if (state.isLoading) {
			CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
		}
	}
}