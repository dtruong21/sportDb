package app.el_even.sportdb.presentation.team_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.el_even.sportdb.common.Resource
import app.el_even.sportdb.domain.interactor.get_team.GetTeamFromNameUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
	private val getTeamFromNameUC: GetTeamFromNameUC,
	savedStateHandle: SavedStateHandle
) : ViewModel() {

	private val _state = mutableStateOf(TeamState())
	val state: State<TeamState> = _state

	private val name: String = checkNotNull(savedStateHandle["team"])

	init {
		getTeam(name)
	}

	private fun getTeam(name: String) {
		getTeamFromNameUC(name).onEach { result ->
			when (result) {
				is Resource.Error -> _state.value =
					TeamState(error = result.message ?: "An unexpected error happened")
				is Resource.Loading -> _state.value = TeamState(isLoading = true)
				is Resource.Success -> _state.value = TeamState(team = result.data)
			}
		}.launchIn(viewModelScope)
	}

}