package com.github.eliascoelho911.meudiario.diary.registry.perday

import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import com.github.eliascoelho911.meudiario.diary.registry.RegistryConverter
import java.time.format.DateTimeFormatter

class GroupRegistriesByDay(private val registryConverter: RegistryConverter) {
    private val delimiterDate = "/"
    private val dayMonthFormatter by lazy { DateTimeFormatter.ofPattern("dd${delimiterDate}MMM") }

    fun invoke(registries: List<RegistryDTO>): List<RegistryPerDayVO> =
        registries.groupBy { it.dateTime.format(dayMonthFormatter) }.map { groupedItem ->
            val day = groupedItem.key.split(delimiterDate)[0]
            val month = groupedItem.key.split(delimiterDate)[1].lowercase()
            val registriesVO = registryConverter.convert(groupedItem.value)
            RegistryPerDayVO(day, month, registriesVO)
        }
}