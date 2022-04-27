package com.github.eliascoelho911.meudiario.domain.entities

import com.github.eliascoelho911.meudiario.presenter.diary.vo.RegistryVO
import java.time.LocalDate

data class RegistryPerDay(
    val date: LocalDate,
    val registries: List<RegistryVO>,
)