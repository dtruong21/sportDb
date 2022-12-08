package app.el_even.sportdb.domain.interactor.get_team

import app.el_even.sportdb.common.Resource
import app.el_even.sportdb.common.TeamMapper
import app.el_even.sportdb.domain.model.Team
import app.el_even.sportdb.domain.repository.SportDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTeamFromNameUC @Inject constructor(private val repository: SportDbRepository) {
	operator fun invoke(name: String): Flow<Resource<Team>> = flow {
		try {
			emit(Resource.Loading())
			val team = TeamMapper().map(repository.getTeamByName(name))
			emit(Resource.Success(team))
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