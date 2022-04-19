package com.github.eliascoelho911.meudiario.diary.registry.perday

import com.github.eliascoelho911.meudiario.diary.registry.RegistryVO

data class RegistryPerDayVO(
    val day: String,
    val month: String,
    val registries: List<RegistryVO>,
)
