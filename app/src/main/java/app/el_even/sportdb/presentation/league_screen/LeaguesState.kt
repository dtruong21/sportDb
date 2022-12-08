package app.el_even.sportdb.presentation.league_screen

import app.el_even.sportdb.domain.model.League

data class LeaguesState(
	val isLoading: Boolean = false,
	val leagues: List<League> = emptyList(),
	val error: String = ""
)
