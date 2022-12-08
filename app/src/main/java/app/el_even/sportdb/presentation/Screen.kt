package app.el_even.sportdb.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.el_even.sportdb.presentation.league_screen.LeagueScreen
import app.el_even.sportdb.presentation.team_screen.TeamScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

sealed class Screen(val route: String) {
	object LeagueScreen : Screen("league")
	object TeamScreen : Screen("team")
}

@ExperimentalMaterialApi
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(navHostController: NavHostController, paddingValues: PaddingValues) {
	AnimatedNavHost(
		navController = navHostController,
		startDestination = Screen.LeagueScreen.route,
		modifier = Modifier.padding(paddingValues)
	) {
		composable(Screen.LeagueScreen.route) {
			LeagueScreen(navController = navHostController)
		}
		composable(
			Screen.TeamScreen.route + "/{team}",
			arguments = listOf(navArgument("team") { type = NavType.StringType })
		) {
			TeamScreen()
		}
	}

}