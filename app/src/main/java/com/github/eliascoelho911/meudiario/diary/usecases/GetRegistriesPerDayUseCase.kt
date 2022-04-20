package com.github.eliascoelho911.meudiario.diary.usecases

import com.github.eliascoelho911.meudiario.data.registry.RegistryRepository
import com.github.eliascoelho911.meudiario.diary.helpers.GroupRegistriesByDay
import com.github.eliascoelho911.meudiario.diary.vo.RegistryPerDayVO
import kotlinx.coroutines.flow.map

class GetRegistriesPerDayUseCase(
    private val groupRegistriesByDay: GroupRegistriesByDay,
    private val repository: RegistryRepository,
) {
    fun invoke() = repository.getAll().map {
        groupRegistriesByDay.invoke(it).sortDays().sortRegistries()
    }

    private fun List<RegistryPerDayVO>.sortDays() = sortedByDescending { it.date }

    private fun List<RegistryPerDayVO>.sortRegistries() =
        map { data ->
            val registriesSorted = data.registries.sortedByDescending { it.time }
            data.copy(registries = registriesSorted)
        }
}
