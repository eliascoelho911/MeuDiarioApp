package com.github.eliascoelho911.meudiario.diary.usecases

import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import com.github.eliascoelho911.meudiario.data.registry.RegistryRepository
import com.github.eliascoelho911.meudiario.diary.converters.RegistryConverter
import com.github.eliascoelho911.meudiario.diary.vo.RegistryPerDayVO
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.flow.map

class GetRegistriesPerDayUseCase(
    private val repository: RegistryRepository,
    private val registryConverter: RegistryConverter,
) {
    private val monthFormatter by lazy { DateTimeFormatter.ofPattern("MMM") }

    fun invoke() = repository.getAll().map {
        groupRegistriesByDay(it).sortDays().sortRegistries()
    }

    private fun groupRegistriesByDay(registries: List<RegistryDTO>) =
        registries.groupBy { it.dateTime.toLocalDate() }.map { groupedItem ->
            val date = groupedItem.key
            val day = date.dayOfMonth.toString()
            val month = date.format(monthFormatter)
            val registriesVO = registryConverter.convert(groupedItem.value)
            RegistryPerDayVO(day, month, date, registriesVO)
        }

    private fun List<RegistryPerDayVO>.sortDays() = sortedByDescending { it.date }

    private fun List<RegistryPerDayVO>.sortRegistries() =
        map { data ->
            val registriesSorted = data.registries.sortedByDescending { it.time }
            data.copy(registries = registriesSorted)
        }
}
