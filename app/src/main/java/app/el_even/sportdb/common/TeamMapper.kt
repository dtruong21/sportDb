package app.el_even.sportdb.common

import app.el_even.sportdb.data.remote.dto.team.TeamDto
import app.el_even.sportdb.domain.model.Team

class TeamMapper : Mapper<TeamDto, Team>() {
	override fun map(obj: TeamDto): Team = Team(
		obj.strTeam ?: "",
		obj.strAlternate ?: "",
		obj.strLeague ?: "",
		obj.strStadium ?: "",
		obj.strKeywords ?: "",
		obj.strDescriptionEN ?: obj.strDescriptionFR ?: "",
		obj.strTeamBanner ?: "",
		obj.strCountry ?: "",
		obj.strTeamBadge ?: ""
	)
}