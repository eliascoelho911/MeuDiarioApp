package com.github.eliascoelho911.meudiario.data.repository

import com.github.eliascoelho911.meudiario.domain.entities.Mood
import com.github.eliascoelho911.meudiario.domain.entities.Registry
import com.github.eliascoelho911.meudiario.domain.repositories.RegistryRepository
import java.time.LocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRegistryRepository : RegistryRepository {
    private val currentTime = LocalDateTime.now()

    override fun getAll(): Flow<List<Registry>> =
        flow {
            val allRegistries = mutableListOf<Registry>()
            for (i in 11 until 20) {
                allRegistries.add(Registry(i.toLong(),
                    currentTime.minusDays(i.toLong()),
                    "item $i",
                    "Corpo do item $i é esse aqui",
                    Mood.NEUTRAL))
            }
            for (i in 0 until 10) {
                allRegistries.add(Registry(i.toLong(),
                    currentTime.minusMinutes(i.toLong()),
                    "item $i",
                    "Corpo do item $i é esse aqui",
                    Mood.NEUTRAL))
            }
            emit(allRegistries)
        }
}