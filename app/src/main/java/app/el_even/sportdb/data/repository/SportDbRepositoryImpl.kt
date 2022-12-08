package app.el_even.sportdb.data.repository

import app.el_even.sportdb.common.Mapper
import app.el_even.sportdb.data.local.model.LeagueEntity
import app.el_even.sportdb.data.remote.api.SportDbApi
import app.el_even.sportdb.data.remote.dto.leagues.LeagueDto
import app.el_even.sportdb.data.remote.dto.team.TeamDto
import app.el_even.sportdb.domain.repository.SportDbRepository
import app.el_even.sportdb.framework.SportDbDatabase
import javax.inject.Inject

class SportDbRepositoryImpl @Inject constructor(
	private val api: SportDbApi,
	private val database: SportDbDatabase
) : SportDbRepository {

	override suspend fun getTeamsByLeague(league: String): List<TeamDto> =
		api.getTeamsFromLeague(league).teams

	override suspend fun getLeagues(): List<LeagueEntity> {
		val dao = database.getLeagueDao()
		val leagues = api.getAllLeagues().leagues.map {
			LeagueEntityMapper().map(it)
		}
		dao.removeAll()
		dao.insertAll(leagues)
		return leagues
	}

	override suspend fun getTeamByName(team: String): TeamDto =
		api.getTeamFromName(team).teams[0]

	private class LeagueEntityMapper : Mapper<LeagueDto, LeagueEntity>() {
		override fun map(obj: LeagueDto): LeagueEntity = LeagueEntity(
			obj.idLeague ?: "",
			obj.strLeague ?: ""
		)
	}
}