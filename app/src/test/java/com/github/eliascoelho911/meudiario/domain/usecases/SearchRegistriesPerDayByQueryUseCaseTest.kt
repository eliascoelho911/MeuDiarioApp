package com.github.eliascoelho911.meudiario.domain.usecases

import com.github.eliascoelho911.meudiario.domain.entities.Mood
import com.github.eliascoelho911.meudiario.domain.entities.Registry
import com.github.eliascoelho911.meudiario.domain.entities.RegistryPerDay
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchRegistriesPerDayByQueryUseCaseTest {
    private val now = LocalDateTime.now()
    private val data = listOf(RegistryPerDay(now.toLocalDate(), listOf(
        Registry(0, now, "title1", "body1", Mood.NEUTRAL),
        Registry(1, now, "title2", "body2", Mood.NEUTRAL)
    )))
    private val getRegistriesPerDayUseCase = mockk<GetRegistriesPerDayUseCase> {
        every { this@mockk.invoke() } returns flowOf(data)
    }
    private val searchRegistriesPerDayByQueryUseCase by lazy {
        SearchRegistriesPerDayByQueryUseCase(getRegistriesPerDayUseCase)
    }

    @Test
    fun givenSuccess_whenToSearchByTitle1WithUpperCase_shouldReturnRegistryWithIdEqualTo0() {
        val result = searchRegistriesPerDayByQueryUseCase.invoke("TITLE1")

        runBlocking {
            result.collect {
                assertEquals(1, it.size)
                assertEquals(1, it.first().registries.size)
                assertEquals(0, it.first().registries.first().id)
            }
        }
    }

    @Test
    fun givenSuccess_whenToSearchByTitle1WithLowerCase_shouldReturnRegistryWithIdEqualTo0() {
        val result = searchRegistriesPerDayByQueryUseCase.invoke("title1")

        runBlocking {
            result.collect {
                assertEquals(1, it.size)
                assertEquals(1, it.first().registries.size)
                assertEquals(0, it.first().registries.first().id)
            }
        }
    }

    @Test
    fun givenSuccess_whenToSearchByBody2_shouldReturnRegistryWithIdEqualTo1() {
        val result = searchRegistriesPerDayByQueryUseCase.invoke("body2")

        runBlocking {
            result.collect {
                assertEquals(1, it.size)
                assertEquals(1, it.first().registries.size)
                assertEquals(1, it.first().registries.first().id)
            }
        }
    }
}