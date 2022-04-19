package com.github.eliascoelho911.meudiario.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.eliascoelho911.meudiario.data.registry.RegistryRepository
import com.github.eliascoelho911.meudiario.diary.registry.perday.GroupRegistriesByDay
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.map

class DiaryViewModel(
    registryRepository: RegistryRepository,
    context: CoroutineContext,
    private val groupRegistriesByDay: GroupRegistriesByDay,
) : ViewModel() {
    val registriesPerDay = registryRepository.getAll()
        .map { groupRegistriesByDay.invoke(it) }.asLiveData(context)
}