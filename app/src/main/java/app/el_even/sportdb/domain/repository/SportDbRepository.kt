package app.el_even.sportdb.domain.repository

import app.el_even.sportdb.data.local.model.LeagueEntity
import app.el_even.sportdb.data.remote.dto.team.TeamDto

interface SportDbRepository {
	suspend fun getTeamsByLeague(league: String): List<TeamDto>

	suspend fun getLeagues(): List<LeagueEntity>

	suspend fun getTeamByName(team: String): TeamDto
}