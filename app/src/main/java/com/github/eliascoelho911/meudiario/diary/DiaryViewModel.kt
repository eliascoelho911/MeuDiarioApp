package com.github.eliascoelho911.meudiario.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.eliascoelho911.meudiario.diary.usecases.GetRegistriesPerDayUseCase
import kotlin.coroutines.CoroutineContext

class DiaryViewModel(
    context: CoroutineContext,
    getRegistriesPerDayUseCase: GetRegistriesPerDayUseCase,
) : ViewModel() {
    val registriesPerDay = getRegistriesPerDayUseCase.invoke().asLiveData(context)

    fun searchRegistries(query: String) = registriesPerDay.value?.run {
        map { perDayIt ->
            perDayIt.copy(registries = perDayIt.registries.filter {
                it.title.contains(query, ignoreCase = false) or
                        it.body.contains(query, ignoreCase = true)
            })
        }.filter { it.registries.isNotEmpty() }
    } ?: emptyList()
}