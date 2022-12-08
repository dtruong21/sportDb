package app.el_even.sportdb.presentation.team_screen

import app.el_even.sportdb.domain.model.Team

data class TeamState(
	val isLoading: Boolean = false,
	val team: Team? = null,
	val error: String = ""
)
