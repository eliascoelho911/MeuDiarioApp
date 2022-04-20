package com.github.eliascoelho911.meudiario.diary.helpers

import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import com.github.eliascoelho911.meudiario.diary.converters.RegistryConverter
import com.github.eliascoelho911.meudiario.diary.vo.RegistryPerDayVO
import java.time.format.DateTimeFormatter

class GroupRegistriesByDay(private val registryConverter: RegistryConverter) {
    private val monthFormatter by lazy { DateTimeFormatter.ofPattern("MMM") }

    fun invoke(registries: List<RegistryDTO>): List<RegistryPerDayVO> =
        registries.groupBy { it.dateTime.toLocalDate() }.map { groupedItem ->
            val date = groupedItem.key
            val day = date.dayOfMonth.toString()
            val month = date.format(monthFormatter)
            val registriesVO = registryConverter.convert(groupedItem.value)
            RegistryPerDayVO(day, month, date, registriesVO)
        }
}