package app.el_even.sportdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.el_even.sportdb.data.local.model.LeagueEntity

@Dao
interface LeagueDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertAll(leagues: List<LeagueEntity>)

	@Query("DELETE FROM LeagueEntity")
	fun removeAll()

	@Query("SELECT * FROM LeagueEntity WHERE name LIKE :name ")
	fun getLeaguesSuggestion(name: String): List<LeagueEntity>
}