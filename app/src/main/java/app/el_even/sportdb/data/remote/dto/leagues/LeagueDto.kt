package app.el_even.sportdb.data.remote.dto.leagues


import com.squareup.moshi.Json

data class LeagueDto(
    @Json(name = "idLeague")
    val idLeague: String?,
    @Json(name = "strLeague")
    val strLeague: String?,
    @Json(name = "strLeagueAlternate")
    val strLeagueAlternate: String?,
    @Json(name = "strSport")
    val strSport: String?
)