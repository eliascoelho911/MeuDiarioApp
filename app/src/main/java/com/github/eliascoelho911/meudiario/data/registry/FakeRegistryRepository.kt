package com.github.eliascoelho911.meudiario.data.registry

import java.time.LocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRegistryRepository : RegistryRepository {
    private val currentTime = LocalDateTime.now()

    override fun getAll(): Flow<List<RegistryDTO>> = flowOf(listOf(
        RegistryDTO(0,
            currentTime,
            "Primeiro item",
            "Corpo do primeiro item é esse aqui",
            Mood.HAPPY),
        RegistryDTO(1,
            currentTime.plusMinutes(30),
            "Segundo item",
            "Corpo do segundo item é esse aqui",
            Mood.SAD),
        RegistryDTO(2,
            currentTime.minusDays(1),
            "Terceiro item",
            "Corpo do terceiro item é esse aqui",
            Mood.NEUTRAL),
        RegistryDTO(3,
            currentTime.minusDays(1).minusHours(1),
            "Quarto item",
            "Corpo do quarto item é esse aqui",
            Mood.VERY_HAPPY),
        RegistryDTO(4,
            currentTime.minusDays(1).minusHours(2),
            "Quinto item",
            "Corpo do quinto item é esse aqui",
            Mood.VERY_SAD),
    ))
}