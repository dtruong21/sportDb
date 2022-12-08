package app.el_even.sportdb.domain.model

data class Team(
	val name: String,
	val fullName: String,
	val league: String,
	val stadium: String,
	val nickname: String,
	val description: String,
	val banner: String,
	val location: String,
	val badge: String
)
