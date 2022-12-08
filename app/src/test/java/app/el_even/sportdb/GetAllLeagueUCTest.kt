package app.el_even.sportdb

import app.el_even.sportdb.data.local.model.LeagueEntity
import app.el_even.sportdb.domain.interactor.get_all_leagues.GetAllLeaguesUC
import app.el_even.sportdb.domain.repository.SportDbRepository
import io.mockk.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.Instant

class GetAllLeagueUCTest {
	private val repository: SportDbRepository = mockk()
	private val classTest = GetAllLeaguesUC(repository)

	@Before
	fun setup() {
		clearAllMocks()
	}

	@Test
	fun `Get All Leagues with successful`() {
		val expectedResult = listOf<LeagueEntity>()
		coEvery { repository.getLeagues() } returns expectedResult
		val result = runTest {
			classTest.invoke().map {
				it.data
			}
		}

		assertEquals(null, result[0])

		coVerify(exactly = 1) {
			repository.getLeagues()
		}

		confirmVerified(repository)

	}

	private fun <T> runTest(block: suspend () -> Flow<T>): List<T> {
		val result = mutableListOf<T>()
		runBlocking {
			launch {
				block()
					.onStart {
						println("${Instant.now()}: starts")
					}
					.onEach {
						println("${Instant.now()}: $it")
					}
					.onCompletion { ex ->
						ex?.let {
							println("${Instant.now()}: $it")
						}
					}
					.collect {
						result.add(it)
					}
			}
		}
		return result
	}

}