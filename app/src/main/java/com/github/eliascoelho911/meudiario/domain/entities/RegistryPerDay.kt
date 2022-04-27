package com.github.eliascoelho911.meudiario.domain.entities

import java.time.LocalDate

data class RegistryPerDay(
    val date: LocalDate,
    val registries: List<Registry>,
)