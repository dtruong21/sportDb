package app.el_even.sportdb.data.remote.api

import app.el_even.sportdb.data.remote.dto.leagues.SportDbAllLeaguesResponse
import app.el_even.sportdb.data.remote.dto.team.SportDbTeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SportDbApi {

	@GET("/api/v1/json/50130162/all_leagues.php")
	suspend fun getAllLeagues(): SportDbAllLeaguesResponse

	@GET("/api/v1/json/50130162/search_all_teams.php")
	suspend fun getTeamsFromLeague(@Query(value = "l") leagueName: String): SportDbTeamResponse

	@GET("/api/v1/json/50130162/searchteams.php")
	suspend fun getTeamFromName(@Query(value = "t") team: String): SportDbTeamResponse
}