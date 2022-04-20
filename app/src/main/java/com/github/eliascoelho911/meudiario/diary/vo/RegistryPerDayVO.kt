package com.github.eliascoelho911.meudiario.diary.vo

import java.time.LocalDate

data class RegistryPerDayVO(
    val day: String,
    val month: String,
    val date: LocalDate,
    val registries: List<RegistryVO>,
)
