package app.el_even.sportdb.common

import app.el_even.sportdb.data.local.model.LeagueEntity
import app.el_even.sportdb.domain.model.League

class LeagueMapper : Mapper<LeagueEntity, League>() {
	override fun map(obj: LeagueEntity): League = League(obj.name)
}