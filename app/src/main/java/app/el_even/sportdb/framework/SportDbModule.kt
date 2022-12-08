package app.el_even.sportdb.framework

import app.el_even.sportdb.common.Constant
import app.el_even.sportdb.data.remote.api.SportDbApi
import app.el_even.sportdb.data.repository.SportDbRepositoryImpl
import app.el_even.sportdb.domain.repository.SportDbRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SportDbModule {

	@Provides
	@Singleton
	fun provideSportDbApi(): SportDbApi = Retrofit.Builder()
		.baseUrl(Constant.BASE_URL)
		.addConverterFactory(
			MoshiConverterFactory.create(
				Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
			)
		)
		.addCallAdapterFactory(CoroutineCallAdapterFactory())
		.build()
		.create(SportDbApi::class.java)

	@Provides
	@Singleton
	fun provideSportDbDatabase(): SportDbDatabase = SportDbDatabase.getInstance()

	@Provides
	@Singleton
	fun provideSportDbRepository(api: SportDbApi, database: SportDbDatabase): SportDbRepository =
		SportDbRepositoryImpl(api, database)
}