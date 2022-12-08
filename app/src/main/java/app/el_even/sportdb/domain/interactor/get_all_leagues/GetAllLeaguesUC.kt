package app.el_even.sportdb.domain.interactor.get_all_leagues

import app.el_even.sportdb.common.LeagueMapper
import app.el_even.sportdb.common.Resource
import app.el_even.sportdb.domain.model.League
import app.el_even.sportdb.domain.repository.SportDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllLeaguesUC @Inject constructor(private val repository: SportDbRepository) {
	operator fun invoke(): Flow<Resource<List<League>>> = flow {
		try {
			emit(Resource.Loading())
			val leagues = LeagueMapper().map(repository.getLeagues())
			emit(Resource.Success(leagues))
		} catch (e: HttpException) {
			emit(
				Resource.Error(
					e.localizedMessage ?: "An unexpected error happened with ${e.code()}"
				)
			)
		} catch (e: IOException) {
			emit(Resource.Error("Couldn't reach server. Check your internet connection."))
		}
	}
}