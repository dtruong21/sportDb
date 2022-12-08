package app.el_even.sportdb.data.remote.dto.team

import com.squareup.moshi.Json

data class SportDbTeamResponse(
    @Json(name = "teams")
    val teams: List<TeamDto>
)