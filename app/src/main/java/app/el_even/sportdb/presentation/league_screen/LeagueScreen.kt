package app.el_even.sportdb.presentation.league_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.el_even.sportdb.domain.model.Team
import app.el_even.sportdb.presentation.Screen
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun LeagueScreen(navController: NavController, viewModel: LeagueViewModel = hiltViewModel()) {
	val leagues = viewModel.leagues.value.leagues.map { it.name }
	val text = remember { mutableStateOf("") }

	Column() {
		Row() {
			TextFieldAutocomplete(list = leagues, setValue = {
				text.value = it
				viewModel.getTeamsFromLeague(it)
			})
		}
		TeamListScreen(navController = navController)
	}

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldAutocomplete(
	list: List<String>,
	setValue: (String) -> Unit
) {
	var text = remember { mutableStateOf("") }  // text in textfield
	var expanded = remember { mutableStateOf(false) } // for dropdownmenu
	var modifier: Modifier //
	var dropList = remember { mutableStateOf(listOf("")) }
	dropList.value = list.filter { it.contains(text.value, ignoreCase = true) }

	Box(
		Modifier
			.padding(10.dp)
			.fillMaxWidth()
	) {
		Column(
			Modifier
				.fillMaxWidth()
				.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally
		) {
			Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
				TextField(value = text.value,
					modifier = Modifier
						.fillMaxWidth(),
					onValueChange = {
						text.value = it

					})
				if (dropList.value.isNotEmpty() && text.value != "" && text.value.length > 3) {

					modifier = if (dropList.value.size > 7) {
						Modifier.height(300.dp)
					} else {
						Modifier
					}

					expanded.value = !(dropList.value.size == 1 && dropList.value[0] == text.value)

					DropdownMenu(
						expanded = expanded.value,
						onDismissRequest = { expanded.value = false },
						properties = PopupProperties(focusable = false),
						modifier = modifier.fillMaxWidth()

					) {
						dropList.value.forEach {
							DropdownMenuItem(onClick = {
								text.value = it
								setValue(it)
								dropList.value = emptyList()
								expanded.value = false

							}) {
								Text(text = it)
							}
						}
					}
				} else {
					expanded.value = false
				}
			}
		}
	}
}

@Composable
fun TeamListScreen(
	navController: NavController,
	viewModel: LeagueViewModel = hiltViewModel()
) {
	val teams = viewModel.teams.value
	val numberOfItemsByRow = LocalConfiguration.current.screenWidthDp / 200
	Box(modifier = Modifier.fillMaxWidth()) {
		if (teams.teams.isNotEmpty()) {
			LazyVerticalGrid(columns = GridCells.Fixed(2)) {
				items(teams.teams.chunked(numberOfItemsByRow)) { rowItems ->
					Row(
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier.padding(4.dp)
					) {
						rowItems.forEach { team ->
							TeamListItem(team = team, onItemClick = {
								navController.navigate(Screen.TeamScreen.route + "/${team.name}")
							})
						}
					}

				}
			}
		}

		if (teams.error.isNotBlank()) {
			Text(
				text = teams.error,
				color = MaterialTheme.colors.error,
				textAlign = TextAlign.Center,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 20.dp)
					.align(Alignment.Center)
			)
		}
		if (teams.isLoading) {
			CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
		}

	}
}

@Composable
fun TeamListItem(
	team: Team,
	onItemClick: (Team) -> Unit
) {
	Row(modifier = Modifier
		.fillMaxWidth()
		.clickable { onItemClick(team) }
		.padding(20.dp),
		horizontalArrangement = Arrangement.SpaceBetween) {
		GlideImage(
			imageModel = team.badge,
			modifier = Modifier,
			loading = {
				CircularProgressIndicator(
					modifier = Modifier.fillMaxSize()
				)
			},
			failure = {
				Text(text = "Image request failed")
			}
		)
	}
}