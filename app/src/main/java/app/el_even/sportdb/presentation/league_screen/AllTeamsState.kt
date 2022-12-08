package app.el_even.sportdb.presentation.league_screen

import app.el_even.sportdb.domain.model.Team

data class AllTeamsState(
	val isLoading: Boolean = false,
	val teams: List<Team> = emptyList(),
	val error: String = ""
)
