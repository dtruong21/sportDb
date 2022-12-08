package app.el_even.sportdb.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import app.el_even.sportdb.ui.theme.SportDBTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SportDBTheme {
				// A surface container using the 'background' color from the theme
				MainScreen()
			}
		}
	}
}

@OptIn(
	ExperimentalAnimationApi::class,
	ExperimentalMaterialApi::class
)
@Composable
fun MainScreen() {
	val navController = rememberAnimatedNavController()
	Scaffold() {
		NavigationGraph(navHostController = navController, paddingValues = it)
	}
}