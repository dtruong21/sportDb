package app.el_even.sportdb.presentation.league_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.el_even.sportdb.common.Resource
import app.el_even.sportdb.domain.interactor.get_all_leagues.GetAllLeaguesUC
import app.el_even.sportdb.domain.interactor.get_teams_from_leagues.GetTeamsFromLeagueUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(
	private val getAllLeaguesUC: GetAllLeaguesUC,
	private val getTeamsFromLeagueUC: GetTeamsFromLeagueUC
) : ViewModel() {

	private val _leagues = mutableStateOf(LeaguesState())
	val leagues: State<LeaguesState> = _leagues

	private val _teams = mutableStateOf(AllTeamsState())
	val teams: State<AllTeamsState> = _teams

	init {
		getLeagues()
	}

	private fun getLeagues() {
		getAllLeaguesUC().onEach { result ->
			when (result) {
				is Resource.Loading -> _leagues.value = LeaguesState(isLoading = true)
				is Resource.Error -> _leagues.value =
					LeaguesState(error = result.message ?: "An unexpected error happened")
				is Resource.Success -> _leagues.value =
					LeaguesState(leagues = result.data ?: emptyList())
			}
		}.launchIn(viewModelScope)
	}

	fun getTeamsFromLeague(league: String) {
		getTeamsFromLeagueUC(league).onEach { result ->
			when (result) {
				is Resource.Loading -> _teams.value = AllTeamsState(isLoading = true)
				is Resource.Error -> _teams.value =
					AllTeamsState(error = result.message ?: "An unexpected error happened")
				is Resource.Success -> _teams.value =
					AllTeamsState(teams = result.data ?: emptyList())
			}
		}.launchIn(viewModelScope)
	}
}