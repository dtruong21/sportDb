package app.el_even.sportdb.framework

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SportDbApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		Timber.plant(Timber.DebugTree())
		SportDbDatabase.init(this)
	}
}