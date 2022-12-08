package app.el_even.sportdb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LeagueEntity(
	@PrimaryKey(autoGenerate = false) val id: String,
	val name: String
)
