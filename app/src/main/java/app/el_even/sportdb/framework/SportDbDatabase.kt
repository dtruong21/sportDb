package app.el_even.sportdb.framework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.el_even.sportdb.common.Constant
import app.el_even.sportdb.data.local.dao.LeagueDao
import app.el_even.sportdb.data.local.model.LeagueEntity

@Database(
	version = Constant.DATABASE_VERSION,
	exportSchema = false,
	entities = [LeagueEntity::class]
)
abstract class SportDbDatabase : RoomDatabase() {
	companion object {
		private var INSTANCE: SportDbDatabase? = null

		fun init(context: Context) {
			if (INSTANCE == null) {
				INSTANCE = Room.databaseBuilder(
					context.applicationContext, SportDbDatabase::class.java,
					Constant.DATABASE_NAME
				).allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build()
			}
		}

		fun getInstance(): SportDbDatabase = INSTANCE!!
	}

	abstract fun getLeagueDao(): LeagueDao
}