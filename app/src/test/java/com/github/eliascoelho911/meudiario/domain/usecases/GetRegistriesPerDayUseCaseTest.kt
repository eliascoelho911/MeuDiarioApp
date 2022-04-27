package com.github.eliascoelho911.meudiario.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.eliascoelho911.meudiario.domain.entities.Mood
import com.github.eliascoelho911.meudiario.domain.entities.Registry
import com.github.eliascoelho911.meudiario.domain.entities.RegistryPerDay
import com.github.eliascoelho911.meudiario.domain.repositories.RegistryRepository
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GetRegistriesPerDayUseCaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val now = LocalDateTime.now()
    private val data = listOf(
        Registry(0, now, "title", "body", Mood.NEUTRAL),
        Registry(1, now.minusDays(1), "title", "body", Mood.NEUTRAL),
        Registry(2, now.minusDays(1), "title", "body", Mood.NEUTRAL)
    )
    private val repository = mockk<RegistryRepository> {
        every { getAll() } returns flowOf(data)
    }
    private val useCase by lazy { GetRegistriesPerDayUseCase(repository) }

    @Test
    fun givenSuccess_whenInvoke_shouldReturnRegistriesPerDay() {
        val dataPerDay = listOf(
            RegistryPerDay(now.toLocalDate(), listOf(data[0])),
            RegistryPerDay(now.minusDays(1).toLocalDate(), data.subList(1, 3)),
        )
        runBlocking {
            useCase.invoke().collect {
                assertEquals(dataPerDay, it)
            }
        }
    }
}