package com.github.eliascoelho911.meudiario.data.registry

import java.time.LocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRegistryRepository : RegistryRepository {
    private val currentTime = LocalDateTime.now()

    override fun getAll(): Flow<List<RegistryDTO>> =
        flow {
            val allRegistries = mutableListOf<RegistryDTO>()
            for (i in 11 until 20) {
                allRegistries.add(RegistryDTO(i,
                    currentTime.minusDays(i.toLong()),
                    "item $i",
                    "Corpo do item $i é esse aqui",
                    Mood.NEUTRAL))
            }
            for (i in 0 until 10) {
                allRegistries.add(RegistryDTO(i,
                    currentTime.minusMinutes(i.toLong()),
                    "item $i",
                    "Corpo do item $i é esse aqui",
                    Mood.NEUTRAL))
            }
            emit(allRegistries)
        }
}