package com.github.eliascoelho911.meudiario.domain.usecases

import com.github.eliascoelho911.meudiario.domain.entities.Registry
import com.github.eliascoelho911.meudiario.domain.entities.RegistryPerDay
import com.github.eliascoelho911.meudiario.domain.repositories.RegistryRepository
import kotlinx.coroutines.flow.map

class GetRegistriesPerDayUseCase(private val repository: RegistryRepository) {

    fun invoke() = repository.getAll().map {
        groupRegistriesByDay(it).sortDays().sortRegistries()
    }

    private fun groupRegistriesByDay(registries: List<Registry>) =
        registries.groupBy { it.dateTime.toLocalDate() }.map { groupedItem ->
            RegistryPerDay(date = groupedItem.key, registries = groupedItem.value)
        }

    private fun List<RegistryPerDay>.sortDays() = sortedByDescending { it.date }

    private fun List<RegistryPerDay>.sortRegistries() = map { data ->
        val registriesSorted = data.registries.sortedByDescending { it.dateTime }
        data.copy(registries = registriesSorted)
    }
}
