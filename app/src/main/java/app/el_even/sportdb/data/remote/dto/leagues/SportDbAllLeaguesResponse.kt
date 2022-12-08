package app.el_even.sportdb.data.remote.dto.leagues


import com.squareup.moshi.Json

data class SportDbAllLeaguesResponse(
    @Json(name = "leagues")
    val leagues: List<LeagueDto>
)